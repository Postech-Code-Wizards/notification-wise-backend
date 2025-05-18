package br.com.wise.notification.application.usecase.notification;

import br.com.wise.notification.application.usecase.notificationLogs.CreateNotificationLogsUseCase;
import br.com.wise.notification.application.usecase.notificationTemplates.RetrieveNotificationTemplatesByNameUseCase;
import br.com.wise.notification.domain.NotificationLogs;
import br.com.wise.notification.domain.NotificationMessage;
import br.com.wise.notification.domain.NotificationTemplates;
import br.com.wise.notification.domain.Notifications;
import br.com.wise.notification.domain.StreamMessage;
import br.com.wise.notification.domain.enums.StatusNotification;
import br.com.wise.notification.domain.enums.StatusNotificationLog;
import br.com.wise.notification.gateway.notification.SendGateway;
import br.com.wise.notification.gateway.notification.rabbitMQ.SendEmailRabbitMQGateway;
import br.com.wise.notification.infrastructure.controller.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.ZonedDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendNotificationUseCase {

    private final RetrieveNotificationTemplatesByNameUseCase retrieveNotificationTemplatesByNameUseCase;
    private final FormatMessageUseCase formatMessageUseCase;
    private final CreateNotificationUseCase createNotificationUseCase;
    private final CreateNotificationLogsUseCase createNotificationLogsUseCase;
    private final SendEmailRabbitMQGateway sendEmailRabbitMQGateway;

    private final ApplicationContext applicationContext;

    public boolean execute(NotificationMessage notificationMessage){

        var notificationTemplates = retrieveNotificationTemplatesByNameUseCase.execute(notificationMessage.getTemplateName());
        if(notificationTemplates == null){
            throw new BusinessException(String.format("The template %s was not found", notificationMessage.getTemplateName()));
        }

        // todo - Validar se sobrou alguma {}, se sim, retorna erro
        var messageFormatted = formatMessageUseCase.replaceMessageValues(notificationTemplates.getMessage(), notificationMessage.getAdditionalInfo());
        var notificationsSaved = saveNotification(notificationMessage, notificationTemplates);

        try {

            var streamMessage = StreamMessage.builder()
                    .notificationId(notificationsSaved.getId())
                    .deliveryMethod(notificationMessage.getDeliveryMethod())
                    .message(messageFormatted).build();

            publishMessage(streamMessage);
            saveNotificationLogs(notificationsSaved.getId(), StatusNotificationLog.SUCCESS, null);

        } catch (Exception e){
            saveNotificationLogs(notificationsSaved.getId(), StatusNotificationLog.FAILURE, e.getMessage());
            log.error(e.getMessage());
            return false;
        }

        return true;
    }

    private Notifications saveNotification(NotificationMessage notificationMessage,
                                           NotificationTemplates notificationTemplates) {

        // todo - validar, criar um converter
        var notification = Notifications.builder()
                .status(StatusNotification.PENDING)
                .deliveryMethod(notificationMessage.getDeliveryMethod())
                .sentAt(ZonedDateTime.now())
                .patientId(notificationMessage.getPatientId())
                .templateId(notificationTemplates.getId()).build();
        return createNotificationUseCase.execute(notification);
    }

    private void saveNotificationLogs(String notificationId,
                                      StatusNotificationLog statusNotificationLog,
                                      String errorMessage){

        // todo - validar, criar um converter
        var notificationLogs = NotificationLogs.builder()
                .status(statusNotificationLog)
                .errorMessage(errorMessage)
                .notificationId(notificationId)
                .attemptDate(ZonedDateTime.now()).build();
        createNotificationLogsUseCase.execute(notificationLogs);
    }

    private void publishMessage(StreamMessage streamMessage){
        SendGateway sendGateway = getSendGateway(streamMessage);
        sendGateway.send(streamMessage);
    }

    private SendGateway getSendGateway(StreamMessage streamMessage) {
        final String qualifierName = String.format("send%sRabbitMQGateway", StringUtils.capitalize(streamMessage.getDeliveryMethod().toString().toLowerCase()));
        return (SendGateway) applicationContext.getBean(qualifierName);
    }


}

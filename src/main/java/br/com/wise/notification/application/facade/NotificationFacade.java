package br.com.wise.notification.application.facade;

import br.com.wise.notification.application.facade.converter.NotificationRequestToDomain;
import br.com.wise.notification.application.usecase.consumer.EmailConsumerUseCase;
import br.com.wise.notification.application.usecase.consumer.SmsConsumerUseCase;
import br.com.wise.notification.application.usecase.consumer.WhatsAppConsumerUseCase;
import br.com.wise.notification.application.usecase.notification.SendNotificationUseCase;
import br.com.wise.notification.domain.StreamMessage;
import br.com.wise.notification.infrastructure.configurarion.NotificationConfig;
import br.com.wise.notification.infrastructure.controller.dtos.request.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationFacade {

    private final NotificationRequestToDomain notificationRequestToDomain;
    private final SendNotificationUseCase sendNotificationUseCase;
    private final EmailConsumerUseCase emailConsumerUseCase;
    private final SmsConsumerUseCase smsConsumerUseCase;
    private final WhatsAppConsumerUseCase whatsAppConsumerUseCase;

    public boolean sendNotification(NotificationRequest notificationRequest){
        var notificationMessage = notificationRequestToDomain.convert(notificationRequest);
        return sendNotificationUseCase.execute(notificationMessage);
    }

    @RabbitListener(queues = {NotificationConfig.NOTIFICATION_QUEUE_NAME})
    public void publishMessageNotification(@Payload NotificationRequest notificationRequest) {
        log.info("[{}] Received general message: {}", this.getClass().getSimpleName(), notificationRequest.toString());
        var notificationMessage = notificationRequestToDomain.convert(notificationRequest);
        sendNotificationUseCase.execute(notificationMessage);
        log.info("[{}] Published general message: {}", this.getClass().getSimpleName(), notificationRequest.toString());
    }

    @RabbitListener(queues = {NotificationConfig.EMAIL_QUEUE_NAME})
    public void emailListener(@Payload StreamMessage message) {
        emailConsumerUseCase.execute(message);
    }

    @RabbitListener(queues = {NotificationConfig.SMS_QUEUE_NAME})
    public void smsListener(@Payload StreamMessage message) {
        smsConsumerUseCase.execute(message);
    }

    @RabbitListener(queues = {NotificationConfig.WHATSAPP_QUEUE_NAME})
    public void whatsappListener(@Payload StreamMessage message) {
        whatsAppConsumerUseCase.execute(message);
    }

}

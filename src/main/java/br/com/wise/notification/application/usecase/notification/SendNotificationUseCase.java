package br.com.wise.notification.application.usecase.notification;

import br.com.wise.notification.application.usecase.notificationlogs.CreateNotificationLogsUseCase;
import br.com.wise.notification.application.usecase.notificationtemplates.RetrieveNotificationTemplatesByNameUseCase;
import br.com.wise.notification.domain.NotificationMessage;
import br.com.wise.notification.domain.enums.StatusNotificationLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendNotificationUseCase {

    private final RetrieveNotificationTemplatesByNameUseCase retrieveNotificationTemplatesByNameUseCase;
    private final FormatMessageUseCase formatMessageUseCase;
    private final CreateNotificationUseCase createNotificationUseCase;
    private final CreateNotificationLogsUseCase createNotificationLogsUseCase;
    private final PublishMessageUseCase publishMessageUseCase;

    public boolean execute(NotificationMessage notificationMessage){

        var notificationTemplates = retrieveNotificationTemplatesByNameUseCase.execute(notificationMessage.getTemplateName());
        var messageFormatted = formatMessageUseCase.replaceMessageValues(notificationTemplates.getMessage(), notificationMessage.getAdditionalInfo());
        var notificationsSaved = createNotificationUseCase.execute(notificationMessage, notificationTemplates);

        try {
            publishMessageUseCase.publishMessage(notificationsSaved.getId(), notificationMessage, messageFormatted);
            createNotificationLogsUseCase.execute(notificationsSaved.getId(), StatusNotificationLog.SUCCESS, null);
        } catch (Exception e){
            createNotificationLogsUseCase.execute(notificationsSaved.getId(), StatusNotificationLog.FAILURE, e.getMessage());
            log.error(e.getMessage());
            return false;
        }
        return true;
    }
}
package br.com.wise.notification.application.usecase.notificationtemplates;

import br.com.wise.notification.domain.NotificationTemplates;
import br.com.wise.notification.gateway.database.NotificationTemplatesGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrieveNotificationTemplatesByIdUseCase {

    private final NotificationTemplatesGateway notificationTemplatesGateway;

    public NotificationTemplates execute(String id){
        return notificationTemplatesGateway.findById(id);
    }
}

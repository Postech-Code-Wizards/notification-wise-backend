package br.com.wise.notification.application.usecase.notificationTemplates;

import br.com.wise.notification.domain.NotificationTemplates;
import br.com.wise.notification.gateway.database.NotificationTemplatesGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RetrieveNotificationTemplatesAllUseCase {

    private final NotificationTemplatesGateway notificationTemplatesGateway;

    public List<NotificationTemplates> execute(){
        return notificationTemplatesGateway.findAll();
    }
}

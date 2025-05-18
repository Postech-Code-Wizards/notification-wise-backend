package br.com.wise.notification.application.usecase.notificationTemplates;

import br.com.wise.notification.domain.NotificationTemplates;
import br.com.wise.notification.gateway.database.NotificationTemplatesGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNotificationTemplatesUseCase {

    private final NotificationTemplatesGateway notificationTemplatesGateway;

    public NotificationTemplates execute(NotificationTemplates notificationTemplates) {
        return notificationTemplatesGateway.save(notificationTemplates);
    }
}

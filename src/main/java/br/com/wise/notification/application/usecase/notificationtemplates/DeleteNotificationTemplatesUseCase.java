package br.com.wise.notification.application.usecase.notificationtemplates;

import br.com.wise.notification.gateway.database.NotificationTemplatesGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteNotificationTemplatesUseCase {

    private final NotificationTemplatesGateway notificationTemplatesGateway;

    public boolean execute(String id){
        return notificationTemplatesGateway.delete(id);
    }
}
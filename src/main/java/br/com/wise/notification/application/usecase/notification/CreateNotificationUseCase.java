package br.com.wise.notification.application.usecase.notification;

import br.com.wise.notification.domain.Notifications;
import br.com.wise.notification.gateway.database.NotificationsGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNotificationUseCase {

    private final NotificationsGateway notificationsGateway;

    public Notifications execute(Notifications notifications) {
        return notificationsGateway.save(notifications);
    }
}

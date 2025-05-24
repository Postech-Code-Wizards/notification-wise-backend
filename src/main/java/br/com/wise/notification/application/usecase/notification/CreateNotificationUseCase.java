package br.com.wise.notification.application.usecase.notification;

import br.com.wise.notification.domain.NotificationMessage;
import br.com.wise.notification.domain.NotificationTemplates;
import br.com.wise.notification.domain.Notifications;
import br.com.wise.notification.domain.enums.StatusNotification;
import br.com.wise.notification.gateway.database.NotificationsGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class CreateNotificationUseCase {

    private final NotificationsGateway notificationsGateway;

    public Notifications execute(NotificationMessage notificationMessage,
                                 NotificationTemplates notificationTemplates) {
        var notifications = populateNotification(notificationMessage, notificationTemplates);
        return notificationsGateway.save(notifications);
    }

    private static Notifications populateNotification(NotificationMessage notificationMessage, NotificationTemplates notificationTemplates) {
        return Notifications.builder()
                .status(StatusNotification.PENDING)
                .deliveryMethod(notificationMessage.getDeliveryMethod())
                .sentAt(ZonedDateTime.now())
                .patientId(notificationMessage.getPatientId())
                .recipient(notificationMessage.getRecipient())
                .templateId(notificationTemplates.getId()).build();
    }
}

package br.com.wise.notification.application.usecase.notification;

import br.com.wise.notification.domain.Notifications;
import br.com.wise.notification.domain.enums.StatusNotification;
import br.com.wise.notification.gateway.database.NotificationsGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class UpdateStatusNotificationUseCase {

    private final NotificationsGateway notificationsGateway;

    public void execute(String id, StatusNotification statusNotification) {

        var notificationsExists = notificationsGateway.findById(id);
        var notifications = populate(notificationsExists, statusNotification);
        notificationsGateway.save(notifications);
    }

    private static Notifications populate(Notifications notifications, StatusNotification statusNotification) {
        return Notifications.builder()
                .id(notifications.getId())
                .status(statusNotification)
                .deliveryMethod(notifications.getDeliveryMethod())
                .sentAt(ZonedDateTime.now())
                .patientId(notifications.getPatientId())
                .recipient(notifications.getRecipient())
                .templateId(notifications.getId()).build();
    }
}
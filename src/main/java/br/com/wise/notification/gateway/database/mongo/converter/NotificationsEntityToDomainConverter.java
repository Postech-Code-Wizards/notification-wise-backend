package br.com.wise.notification.gateway.database.mongo.converter;

import br.com.wise.notification.domain.Notifications;
import br.com.wise.notification.gateway.database.mongo.entities.NotificationsEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationsEntityToDomainConverter {

    public Notifications convert(NotificationsEntity source) {
        return Notifications.builder()
                .id(source.getId())
                .deliveryMethod(source.getDeliveryMethod())
                .patientId(source.getPatientId())
                .status(source.getStatus())
                .sentAt(source.getSentAt())
                .templateId(source.getTemplateId()).build();
    }
}

package br.com.wise.notification.gateway.database.mongo.converter;

import br.com.wise.notification.domain.Notifications;
import br.com.wise.notification.gateway.database.mongo.entities.NotificationsEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.instancio.Instancio.create;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationsEntityToDomainConverterTest {

    private final NotificationsEntityToDomainConverter converter = new NotificationsEntityToDomainConverter();

    @Test
    @DisplayName("Should convert NotificationsEntity to Notifications successfully")
    void shouldConvertEntityToDomainSuccessfully() {
        NotificationsEntity entity = create(NotificationsEntity.class);

        Notifications result = converter.convert(entity);

        assertEquals(entity.getId(), result.getId());
        assertEquals(entity.getDeliveryMethod(), result.getDeliveryMethod());
        assertEquals(entity.getPatientId(), result.getPatientId());
        assertEquals(entity.getStatus(), result.getStatus());
        assertEquals(entity.getSentAt(), result.getSentAt());
        assertEquals(entity.getTemplateId(), result.getTemplateId());
    }
}
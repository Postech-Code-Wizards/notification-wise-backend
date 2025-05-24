package br.com.wise.notification.gateway.database.mongo.converter;

import br.com.wise.notification.domain.Notifications;
import br.com.wise.notification.gateway.database.mongo.entities.NotificationsEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.instancio.Instancio.create;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationsDomainToEntityConverterTest {

    private final NotificationsDomainToEntityConverter converter = new NotificationsDomainToEntityConverter();

    @Test
    @DisplayName("Should convert Notifications domain to NotificationsEntity successfully")
    void shouldConvertDomainToEntitySuccessfully() {
        Notifications domain = create(Notifications.class);

        NotificationsEntity result = converter.convert(domain);

        assertEquals(domain.getId(), result.getId());
        assertEquals(domain.getPatientId(), result.getPatientId());
        assertEquals(domain.getRecipient(), result.getRecipient());
        assertEquals(domain.getStatus(), result.getStatus());
    }
}
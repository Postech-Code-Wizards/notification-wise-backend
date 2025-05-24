package br.com.wise.notification.gateway.database.mongo.converter;

import br.com.wise.notification.domain.NotificationLogs;
import br.com.wise.notification.gateway.database.mongo.entities.NotificationLogsEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.instancio.Instancio.create;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationLogsDomainToEntityConverterTest {

    private final NotificationLogsDomainToEntityConverter converter = new NotificationLogsDomainToEntityConverter();

    @Test
    @DisplayName("Should convert NotificationLogs domain to NotificationLogsEntity successfully")
    void shouldConvertDomainToEntitySuccessfully() {
        NotificationLogs domain = create(NotificationLogs.class);

        NotificationLogsEntity result = converter.convert(domain);

        assertEquals(domain.getId(), result.getId());
        assertEquals(domain.getNotificationId(), result.getNotificationId());
    }
}
package br.com.wise.notification.gateway.database.mongo.converter;

import br.com.wise.notification.domain.NotificationLogs;
import br.com.wise.notification.gateway.database.mongo.entities.NotificationLogsEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.instancio.Instancio.create;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationLogsEntityToDomainConverterTest {

    private final NotificationLogsEntityToDomainConverter converter = new NotificationLogsEntityToDomainConverter();

    @Test
    @DisplayName("Should convert NotificationLogsEntity to NotificationLogs successfully")
    void shouldConvertEntityToDomainSuccessfully() {
        NotificationLogsEntity entity = create(NotificationLogsEntity.class);

        NotificationLogs result = converter.convert(entity);

        assertEquals(entity.getId(), result.getId());
        assertEquals(entity.getNotificationId(), result.getNotificationId());
        assertEquals(entity.getStatus(), result.getStatus());
        assertEquals(entity.getErrorMessage(), result.getErrorMessage());
        assertEquals(entity.getAttemptDate(), result.getAttemptDate());
    }
}
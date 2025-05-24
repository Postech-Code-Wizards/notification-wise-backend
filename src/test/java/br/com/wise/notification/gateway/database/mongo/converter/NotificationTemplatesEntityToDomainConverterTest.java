package br.com.wise.notification.gateway.database.mongo.converter;

import br.com.wise.notification.domain.NotificationTemplates;
import br.com.wise.notification.gateway.database.mongo.entities.NotificationTemplatesEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.instancio.Instancio.create;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationTemplatesEntityToDomainConverterTest {

    private final NotificationTemplatesEntityToDomainConverter converter = new NotificationTemplatesEntityToDomainConverter();

    @Test
    @DisplayName("Should convert NotificationTemplatesEntity to NotificationTemplates successfully")
    void shouldConvertEntityToDomainSuccessfully() {
        NotificationTemplatesEntity entity = create(NotificationTemplatesEntity.class);

        NotificationTemplates result = converter.convert(entity);

        assertEquals(entity.getId(), result.getId());
        assertEquals(entity.getName(), result.getName());
        assertEquals(entity.getMessage(), result.getMessage());
        assertEquals(entity.getCreatedAt(), result.getCreatedAt());
        assertEquals(entity.getUpdatedAt(), result.getUpdatedAt());
    }
}
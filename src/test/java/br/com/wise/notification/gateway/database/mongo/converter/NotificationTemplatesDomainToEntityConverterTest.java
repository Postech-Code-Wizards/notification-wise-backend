package br.com.wise.notification.gateway.database.mongo.converter;

import br.com.wise.notification.domain.NotificationTemplates;
import br.com.wise.notification.gateway.database.mongo.entities.NotificationTemplatesEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.instancio.Instancio.create;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationTemplatesDomainToEntityConverterTest {

    private final NotificationTemplatesDomainToEntityConverter converter = new NotificationTemplatesDomainToEntityConverter();

    @Test
    @DisplayName("Should convert NotificationTemplates domain to NotificationTemplatesEntity successfully")
    void shouldConvertDomainToEntitySuccessfully() {
        NotificationTemplates domain = create(NotificationTemplates.class);

        NotificationTemplatesEntity result = converter.convert(domain);

        assertEquals(domain.getId(), result.getId());
        assertEquals(domain.getName(), result.getName());
        assertEquals(domain.getCreatedAt(), result.getCreatedAt());
        assertEquals(domain.getUpdatedAt(), result.getUpdatedAt());
    }
}
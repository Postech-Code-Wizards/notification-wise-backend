package br.com.wise.notification.application.facade.converter;

import br.com.wise.notification.domain.NotificationTemplates;
import br.com.wise.notification.infrastructure.controller.dtos.response.NotificationTemplatesResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NotificationTemplatesDomainToResponseTest {

    private final NotificationTemplatesDomainToResponse converter = new NotificationTemplatesDomainToResponse();

    @Test
    @DisplayName("Should convert NotificationTemplates to NotificationTemplatesResponse successfully")
    void testConvert() {
        NotificationTemplates notificationTemplates = Instancio.create(NotificationTemplates.class);

        NotificationTemplatesResponse result = converter.convert(notificationTemplates);

        assertNotNull(result);
        assertEquals(notificationTemplates.getId(), result.getId());
        assertEquals(notificationTemplates.getName(), result.getName());
        assertEquals(notificationTemplates.getMessage(), result.getMessage());
        assertEquals(notificationTemplates.getCreatedAt(), result.getCreatedAt());
        assertEquals(notificationTemplates.getUpdatedAt(), result.getUpdatedAt());
    }
}
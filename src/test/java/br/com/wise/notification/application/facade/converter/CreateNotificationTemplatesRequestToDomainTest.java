package br.com.wise.notification.application.facade.converter;

import br.com.wise.notification.domain.NotificationTemplates;
import br.com.wise.notification.infrastructure.controller.dtos.request.CreateNotificationTemplatesRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreateNotificationTemplatesRequestToDomainTest {

    private final CreateNotificationTemplatesRequestToDomain converter = new CreateNotificationTemplatesRequestToDomain();

    @Test
    @DisplayName("Should convert CreateNotificationTemplatesRequest to NotificationTemplates successfully")
    void testConvert() {
        CreateNotificationTemplatesRequest request = Instancio.create(CreateNotificationTemplatesRequest.class);

        NotificationTemplates result = converter.convert(request);

        assertNotNull(result);
        assertEquals(request.getName(), result.getName());
        assertEquals(request.getMessage(), result.getMessage());
        assertNotNull(result.getCreatedAt());
        assertNotNull(result.getUpdatedAt());
    }

    @Test
    @DisplayName("Should set createdAt and updatedAt to current time during conversion")
    void testConvertSetsTimestamps() {
        CreateNotificationTemplatesRequest request = Instancio.create(CreateNotificationTemplatesRequest.class);

        NotificationTemplates result = converter.convert(request);

        ZonedDateTime now = ZonedDateTime.now();
        assertNotNull(result.getCreatedAt());
        assertNotNull(result.getUpdatedAt());
        assertEquals(now.getYear(), result.getCreatedAt().getYear());
        assertEquals(now.getYear(), result.getUpdatedAt().getYear());
    }
}
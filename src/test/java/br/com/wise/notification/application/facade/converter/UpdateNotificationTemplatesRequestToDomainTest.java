package br.com.wise.notification.application.facade.converter;

import br.com.wise.notification.domain.NotificationTemplates;
import br.com.wise.notification.infrastructure.controller.dtos.request.UpdateNotificationTemplatesRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UpdateNotificationTemplatesRequestToDomainTest {

    private final UpdateNotificationTemplatesRequestToDomain converter = new UpdateNotificationTemplatesRequestToDomain();

    @Test
    @DisplayName("Should convert UpdateNotificationTemplatesRequest to NotificationTemplates successfully")
    void testConvert() {
        UpdateNotificationTemplatesRequest request = Instancio.create(UpdateNotificationTemplatesRequest.class);
        ZonedDateTime createdAt = ZonedDateTime.now().minusDays(1);

        NotificationTemplates result = converter.convert(request, createdAt);

        assertNotNull(result);
        assertEquals(request.getId(), result.getId());
        assertEquals(request.getName(), result.getName());
        assertEquals(request.getMessage(), result.getMessage());
        assertEquals(createdAt, result.getCreatedAt());
        assertNotNull(result.getUpdatedAt());
    }

    @Test
    @DisplayName("Should set updatedAt to current time during conversion")
    void testConvertSetsUpdatedAt() {
        UpdateNotificationTemplatesRequest request = Instancio.create(UpdateNotificationTemplatesRequest.class);
        ZonedDateTime createdAt = ZonedDateTime.now().minusDays(1);

        NotificationTemplates result = converter.convert(request, createdAt);

        ZonedDateTime now = ZonedDateTime.now();
        assertNotNull(result.getUpdatedAt());
        assertEquals(now.getYear(), result.getUpdatedAt().getYear());
        assertEquals(now.getMonth(), result.getUpdatedAt().getMonth());
        assertEquals(now.getDayOfMonth(), result.getUpdatedAt().getDayOfMonth());
    }
}
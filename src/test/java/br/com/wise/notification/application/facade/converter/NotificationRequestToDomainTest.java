package br.com.wise.notification.application.facade.converter;

import br.com.wise.notification.domain.NotificationMessage;
import br.com.wise.notification.infrastructure.controller.dtos.request.NotificationRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NotificationRequestToDomainTest {

    private final NotificationRequestToDomain converter = new NotificationRequestToDomain();

    @Test
    @DisplayName("Should convert NotificationRequest to NotificationMessage successfully")
    void testConvert() {
        NotificationRequest notificationRequest = Instancio.create(NotificationRequest.class);

        NotificationMessage result = converter.convert(notificationRequest);

        assertNotNull(result);
        assertEquals(notificationRequest.getTemplateName(), result.getTemplateName());
        assertEquals(notificationRequest.getPatientId(), result.getPatientId());
        assertEquals(notificationRequest.getRecipient(), result.getRecipient());
        assertEquals(notificationRequest.getDeliveryMethod(), result.getDeliveryMethod());
        assertEquals(notificationRequest.getAdditionalInfoMap(), result.getAdditionalInfo());
    }
}
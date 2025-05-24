package br.com.wise.notification.application.usecase.notificationtemplates;

import br.com.wise.notification.domain.NotificationTemplates;
import br.com.wise.notification.gateway.database.NotificationTemplatesGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.instancio.Instancio.create;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateNotificationTemplatesUseCaseTest {

    @Mock
    private NotificationTemplatesGateway notificationTemplatesGateway;

    @InjectMocks
    private UpdateNotificationTemplatesUseCase updateNotificationTemplatesUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should update notification template successfully")
    void shouldUpdateNotificationTemplateSuccessfully() {
        NotificationTemplates notificationTemplates = create(NotificationTemplates.class);
        when(notificationTemplatesGateway.update(notificationTemplates)).thenReturn(notificationTemplates);

        NotificationTemplates result = updateNotificationTemplatesUseCase.execute(notificationTemplates);

        assertNotNull(result);
        assertEquals(notificationTemplates, result);
        verify(notificationTemplatesGateway).update(notificationTemplates);
    }

    @Test
    @DisplayName("Should throw exception when update fails")
    void shouldThrowExceptionWhenUpdateFails() {
        NotificationTemplates notificationTemplates = create(NotificationTemplates.class);
        when(notificationTemplatesGateway.update(notificationTemplates)).thenThrow(new RuntimeException("Update failed"));

        try {
            updateNotificationTemplatesUseCase.execute(notificationTemplates);
        } catch (RuntimeException e) {
            assertEquals("Update failed", e.getMessage());
            verify(notificationTemplatesGateway).update(notificationTemplates);
        }
    }
}
package br.com.wise.notification.application.usecase.notificationtemplates;

import br.com.wise.notification.gateway.database.NotificationTemplatesGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.instancio.Instancio.create;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeleteNotificationTemplatesUseCaseTest {

    @Mock
    private NotificationTemplatesGateway notificationTemplatesGateway;

    @InjectMocks
    private DeleteNotificationTemplatesUseCase deleteNotificationTemplatesUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should delete notification template successfully")
    void shouldDeleteNotificationTemplateSuccessfully() {
        String templateId = create(String.class);
        when(notificationTemplatesGateway.delete(templateId)).thenReturn(true);

        boolean result = deleteNotificationTemplatesUseCase.execute(templateId);

        assertTrue(result);
        verify(notificationTemplatesGateway).delete(templateId);
    }

    @Test
    @DisplayName("Should return false when notification template deletion fails")
    void shouldReturnFalseWhenDeletionFails() {
        String templateId = create(String.class);
        when(notificationTemplatesGateway.delete(templateId)).thenReturn(false);

        boolean result = deleteNotificationTemplatesUseCase.execute(templateId);

        assertFalse(result);
        verify(notificationTemplatesGateway).delete(templateId);
    }
}
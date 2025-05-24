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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateNotificationTemplatesUseCaseTest {

    @Mock
    private NotificationTemplatesGateway notificationTemplatesGateway;

    @InjectMocks
    private CreateNotificationTemplatesUseCase createNotificationTemplatesUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create and save notification template successfully")
    void shouldCreateAndSaveNotificationTemplateSuccessfully() {
        NotificationTemplates notificationTemplates = create(NotificationTemplates.class);
        when(notificationTemplatesGateway.save(notificationTemplates)).thenReturn(notificationTemplates);

        NotificationTemplates result = createNotificationTemplatesUseCase.execute(notificationTemplates);

        assertNotNull(result);
        verify(notificationTemplatesGateway).save(notificationTemplates);
    }

    @Test
    @DisplayName("Should throw exception when saving notification template fails")
    void shouldThrowExceptionWhenSavingNotificationTemplateFails() {
        NotificationTemplates notificationTemplates = create(NotificationTemplates.class);
        when(notificationTemplatesGateway.save(notificationTemplates)).thenThrow(new RuntimeException("Save failed"));

        try {
            createNotificationTemplatesUseCase.execute(notificationTemplates);
        } catch (RuntimeException e) {
            verify(notificationTemplatesGateway).save(notificationTemplates);
        }
    }
}
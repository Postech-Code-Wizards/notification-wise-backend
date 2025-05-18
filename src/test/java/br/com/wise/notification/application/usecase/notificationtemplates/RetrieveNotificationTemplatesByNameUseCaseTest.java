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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class RetrieveNotificationTemplatesByNameUseCaseTest {

    @Mock
    private NotificationTemplatesGateway notificationTemplatesGateway;

    @InjectMocks
    private RetrieveNotificationTemplatesByNameUseCase retrieveNotificationTemplatesByNameUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should retrieve notification template by name successfully")
    void shouldRetrieveNotificationTemplateByNameSuccessfully() {
        String name = create(String.class);
        NotificationTemplates notificationTemplate = create(NotificationTemplates.class);
        when(notificationTemplatesGateway.findByName(name)).thenReturn(notificationTemplate);

        NotificationTemplates result = retrieveNotificationTemplatesByNameUseCase.execute(name);

        assertEquals(notificationTemplate, result);
    }

    @Test
    @DisplayName("Should return null when notification template is not found by name")
    void shouldReturnNullWhenNotificationTemplateNotFoundByName() {
        String name = create(String.class);
        when(notificationTemplatesGateway.findByName(name)).thenReturn(null);

        NotificationTemplates result = retrieveNotificationTemplatesByNameUseCase.execute(name);

        assertNull(result);
    }
}
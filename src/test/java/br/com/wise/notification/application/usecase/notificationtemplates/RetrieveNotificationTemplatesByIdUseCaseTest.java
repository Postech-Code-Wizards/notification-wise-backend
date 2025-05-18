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
import static org.mockito.Mockito.when;

class RetrieveNotificationTemplatesByIdUseCaseTest {

    @Mock
    private NotificationTemplatesGateway notificationTemplatesGateway;

    @InjectMocks
    private RetrieveNotificationTemplatesByIdUseCase retrieveNotificationTemplatesByIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should retrieve notification template by ID successfully")
    void shouldRetrieveNotificationTemplateByIdSuccessfully() {
        String id = create(String.class);
        NotificationTemplates notificationTemplate = create(NotificationTemplates.class);
        when(notificationTemplatesGateway.findById(id)).thenReturn(notificationTemplate);

        NotificationTemplates result = retrieveNotificationTemplatesByIdUseCase.execute(id);

        assertNotNull(result);
        assertEquals(notificationTemplate, result);
    }

}
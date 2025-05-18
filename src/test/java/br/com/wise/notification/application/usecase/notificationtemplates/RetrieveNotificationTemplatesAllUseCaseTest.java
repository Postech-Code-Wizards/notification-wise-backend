package br.com.wise.notification.application.usecase.notificationtemplates;

import br.com.wise.notification.domain.NotificationTemplates;
import br.com.wise.notification.gateway.database.NotificationTemplatesGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class RetrieveNotificationTemplatesAllUseCaseTest {

    @Mock
    private NotificationTemplatesGateway notificationTemplatesGateway;

    @InjectMocks
    private RetrieveNotificationTemplatesAllUseCase retrieveNotificationTemplatesAllUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should retrieve all notification templates successfully")
    void shouldRetrieveAllNotificationTemplatesSuccessfully() {
        List<NotificationTemplates> templates = Instancio.createList(NotificationTemplates.class);
        when(notificationTemplatesGateway.findAll()).thenReturn(templates);

        List<NotificationTemplates> result = retrieveNotificationTemplatesAllUseCase.execute();

        assertNotNull(result);
        assertEquals(templates.size(), result.size());
    }

    @Test
    @DisplayName("Should return an empty list when no notification templates are found")
    void shouldReturnEmptyListWhenNoTemplatesFound() {
        when(notificationTemplatesGateway.findAll()).thenReturn(List.of());

        List<NotificationTemplates> result = retrieveNotificationTemplatesAllUseCase.execute();

        assertNotNull(result);
        assertEquals(0, result.size());
    }
}
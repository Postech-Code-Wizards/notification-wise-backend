package br.com.wise.notification.application.usecase.notification;

import br.com.wise.notification.application.usecase.notificationlogs.CreateNotificationLogsUseCase;
import br.com.wise.notification.application.usecase.notificationtemplates.RetrieveNotificationTemplatesByNameUseCase;
import br.com.wise.notification.domain.NotificationMessage;
import br.com.wise.notification.domain.NotificationTemplates;
import br.com.wise.notification.domain.Notifications;
import br.com.wise.notification.domain.enums.StatusNotificationLog;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SendNotificationUseCaseTest {

    @Mock
    private RetrieveNotificationTemplatesByNameUseCase retrieveNotificationTemplatesByNameUseCase;

    @Mock
    private FormatMessageUseCase formatMessageUseCase;

    @Mock
    private CreateNotificationUseCase createNotificationUseCase;

    @Mock
    private CreateNotificationLogsUseCase createNotificationLogsUseCase;

    @Mock
    private PublishMessageUseCase publishMessageUseCase;

    @InjectMocks
    private SendNotificationUseCase sendNotificationUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should execute notification sending successfully")
    void testExecuteSuccess() {
        NotificationMessage notificationMessage = Instancio.create(NotificationMessage.class);
        NotificationTemplates notificationTemplates = Instancio.create(NotificationTemplates.class);
        Notifications notifications = Instancio.create(Notifications.class);

        when(retrieveNotificationTemplatesByNameUseCase.execute(notificationMessage.getTemplateName()))
                .thenReturn(notificationTemplates);
        when(formatMessageUseCase.replaceMessageValues(notificationTemplates.getMessage(), notificationMessage.getAdditionalInfo()))
                .thenReturn("Formatted message");
        when(createNotificationUseCase.execute(notificationMessage, notificationTemplates))
                .thenReturn(notifications);

        boolean result = sendNotificationUseCase.execute(notificationMessage);

        assertTrue(result);
        verify(publishMessageUseCase).publishMessage(notifications.getId(), notificationMessage, "Formatted message");
        verify(createNotificationLogsUseCase).execute(notifications.getId(), StatusNotificationLog.SUCCESS, null);
    }

    @Test
    @DisplayName("Should handle exception and return false when publishing fails")
    void testExecuteFailure() {
        NotificationMessage notificationMessage = Instancio.create(NotificationMessage.class);
        NotificationTemplates notificationTemplates = Instancio.create(NotificationTemplates.class);
        Notifications notifications = Instancio.create(Notifications.class);

        when(retrieveNotificationTemplatesByNameUseCase.execute(notificationMessage.getTemplateName()))
                .thenReturn(notificationTemplates);
        when(formatMessageUseCase.replaceMessageValues(notificationTemplates.getMessage(), notificationMessage.getAdditionalInfo()))
                .thenReturn("Formatted message");
        when(createNotificationUseCase.execute(notificationMessage, notificationTemplates))
                .thenReturn(notifications);
        doThrow(new RuntimeException("Publishing failed")).when(publishMessageUseCase)
                .publishMessage(notifications.getId(), notificationMessage, "Formatted message");

        boolean result = sendNotificationUseCase.execute(notificationMessage);

        assertFalse(result);
        verify(createNotificationLogsUseCase).execute(notifications.getId(), StatusNotificationLog.FAILURE, "Publishing failed");
    }
}
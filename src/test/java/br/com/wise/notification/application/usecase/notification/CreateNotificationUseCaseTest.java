package br.com.wise.notification.application.usecase.notification;

import br.com.wise.notification.domain.NotificationMessage;
import br.com.wise.notification.domain.NotificationTemplates;
import br.com.wise.notification.domain.Notifications;
import br.com.wise.notification.gateway.database.NotificationsGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateNotificationUseCaseTest {

    @Mock
    private NotificationsGateway notificationsGateway;

    @InjectMocks
    private CreateNotificationUseCase createNotificationUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create and save Notifications successfully")
    void testExecuteSuccess() {
        NotificationMessage notificationMessage = Instancio.create(NotificationMessage.class);
        NotificationTemplates notificationTemplates = Instancio.create(NotificationTemplates.class);
        Notifications notifications = Instancio.create(Notifications.class);

        when(notificationsGateway.save(any(Notifications.class))).thenReturn(notifications);

        Notifications result = createNotificationUseCase.execute(notificationMessage, notificationTemplates);

        assertNotNull(result);
        verify(notificationsGateway).save(any(Notifications.class));
    }

}
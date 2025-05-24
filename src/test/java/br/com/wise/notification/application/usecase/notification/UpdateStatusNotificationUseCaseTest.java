package br.com.wise.notification.application.usecase.notification;

import br.com.wise.notification.domain.Notifications;
import br.com.wise.notification.domain.enums.StatusNotification;
import br.com.wise.notification.gateway.database.NotificationsGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.instancio.Instancio.create;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateStatusNotificationUseCaseTest {

    @Mock
    private NotificationsGateway notificationsGateway;

    @InjectMocks
    private UpdateStatusNotificationUseCase updateStatusNotificationUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should update notification status successfully")
    void shouldUpdateNotificationStatusSuccessfully() {
        Notifications existingNotification = create(Notifications.class);
        when(notificationsGateway.findById(existingNotification.getId())).thenReturn(existingNotification);

        assertDoesNotThrow(() -> updateStatusNotificationUseCase.execute(existingNotification.getId(), StatusNotification.SENT));

        verify(notificationsGateway).findById(existingNotification.getId());
        verify(notificationsGateway).save(any(Notifications.class));
    }

}
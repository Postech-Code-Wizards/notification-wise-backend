package br.com.wise.notification.infrastructure.controller;

import br.com.wise.notification.application.facade.NotificationFacade;
import br.com.wise.notification.infrastructure.controller.dtos.request.NotificationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.instancio.Instancio.create;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class NotificationControllerTest {

    @Mock
    private NotificationFacade notificationFacade;

    @InjectMocks
    private NotificationController notificationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should send notification successfully")
    void shouldSendNotificationSuccessfully() {

        NotificationRequest notificationRequest = create(NotificationRequest.class);

        when(notificationFacade.sendNotification(notificationRequest)).thenReturn(true);

        boolean result = notificationController.sendNotification(notificationRequest);

        assertTrue(result);
    }
}
package br.com.wise.notification.application.usecase.notificationlogs;

import br.com.wise.notification.domain.NotificationLogs;
import br.com.wise.notification.domain.enums.StatusNotificationLog;
import br.com.wise.notification.gateway.database.NotificationLogsGateway;
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

class CreateNotificationLogsUseCaseTest {

    @Mock
    private NotificationLogsGateway notificationLogsGateway;

    @InjectMocks
    private CreateNotificationLogsUseCase createNotificationLogsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create and save NotificationLogs successfully")
    void testExecuteSuccess() {
        String notificationId = Instancio.create(String.class);
        StatusNotificationLog status = StatusNotificationLog.SUCCESS;

        NotificationLogs notificationLogs = Instancio.create(NotificationLogs.class);
        when(notificationLogsGateway.save(any(NotificationLogs.class))).thenReturn(notificationLogs);

        NotificationLogs result = createNotificationLogsUseCase.execute(notificationId, status, null);

        assertNotNull(result);
        verify(notificationLogsGateway).save(any(NotificationLogs.class));
    }

}
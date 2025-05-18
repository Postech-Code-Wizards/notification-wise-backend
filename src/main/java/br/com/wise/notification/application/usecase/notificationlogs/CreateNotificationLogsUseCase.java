package br.com.wise.notification.application.usecase.notificationlogs;

import br.com.wise.notification.domain.NotificationLogs;
import br.com.wise.notification.domain.enums.StatusNotificationLog;
import br.com.wise.notification.gateway.database.NotificationLogsGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class CreateNotificationLogsUseCase {

    private final NotificationLogsGateway notificationLogsGateway;

    public NotificationLogs execute(String notificationId,
                                    StatusNotificationLog statusNotificationLog,
                                    String errorMessage){
        var notificationLogs = populate(notificationId, statusNotificationLog, errorMessage);
        return notificationLogsGateway.save(notificationLogs);
    }

    private static NotificationLogs populate(String notificationId,
                                             StatusNotificationLog statusNotificationLog,
                                             String errorMessage) {
        return NotificationLogs.builder()
                .status(statusNotificationLog)
                .errorMessage(errorMessage)
                .notificationId(notificationId)
                .attemptDate(ZonedDateTime.now()).build();
    }
}
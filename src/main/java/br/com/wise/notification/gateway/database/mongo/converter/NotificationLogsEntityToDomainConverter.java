package br.com.wise.notification.gateway.database.mongo.converter;

import br.com.wise.notification.domain.NotificationLogs;
import br.com.wise.notification.gateway.database.mongo.entities.NotificationLogsEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationLogsEntityToDomainConverter {

    public NotificationLogs convert(NotificationLogsEntity source) {
        return NotificationLogs.builder()
                .id(source.getId())
                .notificationId(source.getNotificationId())
                .status(source.getStatus())
                .errorMessage(source.getErrorMessage())
                .attemptDate(source.getAttemptDate()).build();
    }
}
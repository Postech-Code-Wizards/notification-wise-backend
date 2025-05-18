package br.com.wise.notification.domain;

import br.com.wise.notification.domain.enums.StatusNotificationLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Builder
@Getter
@AllArgsConstructor
public class NotificationLogs {
    private String id;
    private String notificationId;
    private ZonedDateTime attemptDate;
    private StatusNotificationLog status;
    private String errorMessage;
}

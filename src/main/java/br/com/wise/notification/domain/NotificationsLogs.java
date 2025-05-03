package br.com.wise.notification.domain;

import br.com.wise.notification.domain.enums.StatusNotificationLog;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class NotificationsLogs {
    private String id;
    private String notificationId;
    private ZonedDateTime attemptDate;
    private StatusNotificationLog status;
    private String errorMessage;
}

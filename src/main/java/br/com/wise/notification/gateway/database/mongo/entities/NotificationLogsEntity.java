package br.com.wise.notification.gateway.database.mongo.entities;

import br.com.wise.notification.domain.enums.StatusNotificationLog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notification_logs")
public class NotificationLogsEntity {

    @Id
    private String id;

    @Field("notification_id")
    private String notificationId;

    @Field("attempt_date")
    private ZonedDateTime attemptDate;

    @Field("status")
    private StatusNotificationLog status;

    @Field("error_message")
    private String errorMessage;

}

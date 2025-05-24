package br.com.wise.notification.gateway.database.mongo.entities;

import br.com.wise.notification.domain.enums.DeliveryMethod;
import br.com.wise.notification.domain.enums.StatusNotification;
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
@Document(collection = "notification")
public class NotificationsEntity {

    @Id
    private String id;

    @Field("template_id")
    private String templateId;

    @Field("patient_id")
    private Long patientId;

    @Field("recipient")
    private String recipient;

    @Field("status")
    private StatusNotification status;

    @Field("sent_at")
    private ZonedDateTime sentAt;

    @Field("delivery_method")
    private DeliveryMethod deliveryMethod;

}

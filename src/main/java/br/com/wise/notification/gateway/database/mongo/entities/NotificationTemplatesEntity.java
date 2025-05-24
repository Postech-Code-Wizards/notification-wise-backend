package br.com.wise.notification.gateway.database.mongo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notification_templates")
public class NotificationTemplatesEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    @Field("name")
    private String name;

    @Field("message")
    private String message;

    @CreationTimestamp
    @Field("created_at")
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Field("updated_at")
    private ZonedDateTime updatedAt;

}

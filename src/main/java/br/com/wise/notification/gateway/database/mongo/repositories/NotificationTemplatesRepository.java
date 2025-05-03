package br.com.wise.notification.gateway.database.mongo.repositories;

import br.com.wise.notification.gateway.database.mongo.entities.NotificationTemplatesEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationTemplatesRepository extends MongoRepository<NotificationTemplatesEntity, String> {
}

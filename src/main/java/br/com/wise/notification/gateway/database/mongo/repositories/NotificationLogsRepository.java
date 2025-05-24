package br.com.wise.notification.gateway.database.mongo.repositories;

import br.com.wise.notification.gateway.database.mongo.entities.NotificationLogsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationLogsRepository extends MongoRepository<NotificationLogsEntity, String> {

}

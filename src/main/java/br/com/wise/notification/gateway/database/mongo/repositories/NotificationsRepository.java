package br.com.wise.notification.gateway.database.mongo.repositories;

import br.com.wise.notification.gateway.database.mongo.entities.NotificationsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationsRepository extends MongoRepository<NotificationsEntity, String> {

}

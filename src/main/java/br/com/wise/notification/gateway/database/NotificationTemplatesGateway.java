package br.com.wise.notification.gateway.database;

import br.com.wise.notification.domain.NotificationTemplates;

import java.util.List;

public interface NotificationTemplatesGateway {

    NotificationTemplates findById(String id);

    NotificationTemplates findByName(String name);

    List<NotificationTemplates> findAll();

    NotificationTemplates save(NotificationTemplates notificationTemplates);

    NotificationTemplates update(NotificationTemplates notificationTemplates);

    boolean delete(String id);

}

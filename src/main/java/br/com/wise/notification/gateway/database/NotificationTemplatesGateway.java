package br.com.wise.notification.gateway.database;

import br.com.wise.notification.domain.NotificationTemplates;

public interface NotificationTemplatesGateway {

    NotificationTemplates findById(Long id);

    NotificationTemplates findByNameTemplate(String name);

    NotificationTemplates save(NotificationTemplates notificationTemplates);

    void delete(Long id);

}

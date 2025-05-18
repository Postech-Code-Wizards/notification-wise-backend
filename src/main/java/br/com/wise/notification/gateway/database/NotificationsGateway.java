package br.com.wise.notification.gateway.database;

import br.com.wise.notification.domain.Notifications;

public interface NotificationsGateway {

    Notifications findById(String id);

    Notifications save(Notifications notifications);

}

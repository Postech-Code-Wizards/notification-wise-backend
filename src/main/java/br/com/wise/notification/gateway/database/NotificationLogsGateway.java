package br.com.wise.notification.gateway.database;

import br.com.wise.notification.domain.NotificationLogs;

public interface NotificationLogsGateway {

    NotificationLogs save(NotificationLogs notificationLogs);

}

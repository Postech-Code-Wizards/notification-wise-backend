package br.com.wise.notification.application.usecase.notificationLogs;

import br.com.wise.notification.domain.NotificationLogs;
import br.com.wise.notification.gateway.database.NotificationLogsGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNotificationLogsUseCase {

    private final NotificationLogsGateway notificationLogsGateway;

    public NotificationLogs execute(NotificationLogs notificationLogs){
        return notificationLogsGateway.save(notificationLogs);
    }
}

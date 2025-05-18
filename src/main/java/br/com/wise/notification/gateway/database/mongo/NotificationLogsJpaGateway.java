package br.com.wise.notification.gateway.database.mongo;

import br.com.wise.notification.domain.NotificationLogs;
import br.com.wise.notification.gateway.database.NotificationLogsGateway;
import br.com.wise.notification.gateway.database.mongo.converter.NotificationLogsDomainToEntityConverter;
import br.com.wise.notification.gateway.database.mongo.converter.NotificationLogsEntityToDomainConverter;
import br.com.wise.notification.gateway.database.mongo.repositories.NotificationLogsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationLogsJpaGateway implements NotificationLogsGateway {

    private final NotificationLogsRepository notificationLogsRepository;
    private final NotificationLogsDomainToEntityConverter notificationLogsDomainToEntityConverter;
    private final NotificationLogsEntityToDomainConverter notificationLogsEntityToDomainConverter;

    @Override
    public NotificationLogs save(NotificationLogs notificationLogs) {
        var notificationsEntity = notificationLogsDomainToEntityConverter.convert(notificationLogs);
        var notificationLogsEntitySaved = notificationLogsRepository.save(notificationsEntity);
        return notificationLogsEntityToDomainConverter.convert(notificationLogsEntitySaved);
    }

}
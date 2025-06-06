package br.com.wise.notification.gateway.database.mongo;

import br.com.wise.notification.domain.Notifications;
import br.com.wise.notification.gateway.database.NotificationsGateway;
import br.com.wise.notification.gateway.database.mongo.converter.NotificationsDomainToEntityConverter;
import br.com.wise.notification.gateway.database.mongo.converter.NotificationsEntityToDomainConverter;
import br.com.wise.notification.gateway.database.mongo.repositories.NotificationsRepository;
import br.com.wise.notification.infrastructure.controller.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationsJpaGateway implements NotificationsGateway {

    private static final String NOTIFICATIONS_DOES_NOT_EXIST = "Notification not found";

    private final NotificationsRepository notificationsRepository;
    private final NotificationsDomainToEntityConverter notificationsDomainToEntityConverter;
    private final NotificationsEntityToDomainConverter notificationsEntityToDomainConverter;

    @Override
    public Notifications findById(String id) {
        var notificationsEntity = notificationsRepository.findById(id)
                .orElseThrow(() -> new BusinessException(NOTIFICATIONS_DOES_NOT_EXIST));

        return notificationsEntityToDomainConverter.convert(notificationsEntity);
    }

    @Override
    public Notifications save(Notifications notifications) {
        var notificationsEntity = notificationsDomainToEntityConverter.convert(notifications);
        var notificationsEntitySaved = notificationsRepository.save(notificationsEntity);
        return notificationsEntityToDomainConverter.convert(notificationsEntitySaved);
    }

}
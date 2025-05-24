package br.com.wise.notification.gateway.database.mongo.converter;

import br.com.wise.notification.domain.Notifications;
import br.com.wise.notification.gateway.database.mongo.entities.NotificationsEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationsDomainToEntityConverter {

    public NotificationsEntity convert(Notifications source) {
        var mapper = new ModelMapper();
        return mapper.map(source, NotificationsEntity.class);
    }
}

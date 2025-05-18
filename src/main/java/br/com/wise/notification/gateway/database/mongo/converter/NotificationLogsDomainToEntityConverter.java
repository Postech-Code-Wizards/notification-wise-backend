package br.com.wise.notification.gateway.database.mongo.converter;

import br.com.wise.notification.domain.NotificationLogs;
import br.com.wise.notification.gateway.database.mongo.entities.NotificationLogsEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationLogsDomainToEntityConverter {

    public NotificationLogsEntity convert(NotificationLogs source) {
        var mapper = new ModelMapper();
        return mapper.map(source, NotificationLogsEntity.class);
    }
}
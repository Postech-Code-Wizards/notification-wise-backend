package br.com.wise.notification.gateway.database.mongo.converter;

import br.com.wise.notification.domain.NotificationTemplates;
import br.com.wise.notification.gateway.database.mongo.entities.NotificationTemplatesEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationTemplatesDomainToEntityConverter {

    public NotificationTemplatesEntity convert(NotificationTemplates source) {
        var mapper = new ModelMapper();
        return mapper.map(source, NotificationTemplatesEntity.class);
    }
}

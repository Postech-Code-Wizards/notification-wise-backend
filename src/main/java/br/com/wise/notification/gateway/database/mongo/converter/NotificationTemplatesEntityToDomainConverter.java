package br.com.wise.notification.gateway.database.mongo.converter;

import br.com.wise.notification.domain.NotificationTemplates;
import br.com.wise.notification.gateway.database.mongo.entities.NotificationTemplatesEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationTemplatesEntityToDomainConverter {

    public NotificationTemplates convert(NotificationTemplatesEntity source) {
        return NotificationTemplates.builder()
                .id(source.getId())
                .name(source.getName())
                .message(source.getMessage())
                .createdAt(source.getCreatedAt())
                .updatedAt(source.getUpdatedAt()).build();
    }
}

package br.com.wise.notification.application.facade.converter;

import br.com.wise.notification.domain.NotificationTemplates;
import br.com.wise.notification.infrastructure.controller.dtos.request.NotificationTemplatesRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationTemplatesRequestToDomain {

    public NotificationTemplates convert(NotificationTemplatesRequest source) {
        return NotificationTemplates.builder()
                .id(null)
                .name(source.getName())
                .message(source.getMessage())
                .createdAt(null)
                .updatedAt(null)
                .build();
    }

}

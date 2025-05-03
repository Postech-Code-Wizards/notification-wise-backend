package br.com.wise.notification.application.facade.converter;

import br.com.wise.notification.domain.NotificationTemplates;
import br.com.wise.notification.infrastructure.controller.dtos.response.NotificationTemplatesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationTemplatesDomainToResponse {

    public NotificationTemplatesResponse convert(NotificationTemplates source) {
        return NotificationTemplatesResponse.builder()
                .id(source.getId())
                .name(source.getName())
                .message(source.getMessage())
                .createdAt(source.getCreatedAt())
                .updatedAt(source.getUpdatedAt())
                .build();
    }

}

package br.com.wise.notification.application.facade.converter;

import br.com.wise.notification.domain.NotificationTemplates;
import br.com.wise.notification.infrastructure.controller.dtos.request.UpdateNotificationTemplatesRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class UpdateNotificationTemplatesRequestToDomain {

    public NotificationTemplates convert(UpdateNotificationTemplatesRequest updateNotificationTemplatesRequest, ZonedDateTime createdAt) {
        return NotificationTemplates.builder()
                .id(updateNotificationTemplatesRequest.getId())
                .name(updateNotificationTemplatesRequest.getName())
                .message(updateNotificationTemplatesRequest.getMessage())
                .createdAt(createdAt)
                .updatedAt(ZonedDateTime.now())
                .build();
    }

}
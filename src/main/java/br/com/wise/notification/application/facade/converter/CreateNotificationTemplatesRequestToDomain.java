package br.com.wise.notification.application.facade.converter;

import br.com.wise.notification.domain.NotificationTemplates;
import br.com.wise.notification.infrastructure.controller.dtos.request.CreateNotificationTemplatesRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class CreateNotificationTemplatesRequestToDomain {

    public NotificationTemplates convert(CreateNotificationTemplatesRequest createNotificationTemplatesRequest) {
        return NotificationTemplates.builder()
                .id(null)
                .name(createNotificationTemplatesRequest.getName())
                .message(createNotificationTemplatesRequest.getMessage())
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
    }

}
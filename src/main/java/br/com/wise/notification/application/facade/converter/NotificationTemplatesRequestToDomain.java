package br.com.wise.notification.application.facade.converter;

import br.com.wise.notification.domain.NotificationTemplates;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class NotificationTemplatesRequestToDomain {

    public NotificationTemplates convert(String name, String message) {
        return NotificationTemplates.builder()
                .id(null)
                .name(name)
                .message(message)
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
    }

    public NotificationTemplates convert(String id, String name, String message, ZonedDateTime createdAt) {
        return NotificationTemplates.builder()
                .id(id)
                .name(name)
                .message(message)
                .createdAt(createdAt)
                .updatedAt(ZonedDateTime.now())
                .build();
    }

}

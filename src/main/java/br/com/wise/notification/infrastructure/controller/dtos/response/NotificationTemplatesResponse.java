package br.com.wise.notification.infrastructure.controller.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationTemplatesResponse {
    private String id;
    private String name;
    private String message;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}

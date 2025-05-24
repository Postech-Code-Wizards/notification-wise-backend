package br.com.wise.notification.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationTemplates {
    private String id;
    private String name;
    private String message;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}

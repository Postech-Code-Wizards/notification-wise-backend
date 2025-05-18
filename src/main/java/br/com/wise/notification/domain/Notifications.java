package br.com.wise.notification.domain;

import br.com.wise.notification.domain.enums.DeliveryMethod;
import br.com.wise.notification.domain.enums.StatusNotification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Builder
@Getter
@AllArgsConstructor
public class Notifications {
    private String id;
    private String templateId;
    private Long patientId;
    private String recipient;
    private StatusNotification status;
    private ZonedDateTime sentAt;
    private DeliveryMethod deliveryMethod;
}

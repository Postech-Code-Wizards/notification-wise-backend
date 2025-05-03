package br.com.wise.notification.domain;

import br.com.wise.notification.domain.enums.DeliveryMethod;
import br.com.wise.notification.domain.enums.StatusNotification;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class Notifications {
    private String id;
    private String templateId;
    private String patientId;
    private StatusNotification status;
    final ZonedDateTime sentAt;
    private DeliveryMethod deliveryMethod;
}

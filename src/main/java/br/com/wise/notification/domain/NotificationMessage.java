package br.com.wise.notification.domain;

import br.com.wise.notification.domain.enums.DeliveryMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
@AllArgsConstructor
public class NotificationMessage {
    private String templateName;
    private Long patientId;
    private String recipient;
    private DeliveryMethod deliveryMethod;
    private Map<String, String> additionalInfo;
}

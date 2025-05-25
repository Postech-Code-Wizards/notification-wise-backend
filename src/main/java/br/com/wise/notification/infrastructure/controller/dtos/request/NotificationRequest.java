package br.com.wise.notification.infrastructure.controller.dtos.request;

import br.com.wise.notification.domain.enums.DeliveryMethod;
import java.util.Collections;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"templateName", "deliveryMethod", "patientId", "recipient"})
public class NotificationRequest {
    private String templateName;
    private DeliveryMethod deliveryMethod;
    private Long patientId;
    private String recipient;
    private List<KeyValueRequest> additionalInfo;

    public Map<String, String> getAdditionalInfoMap() {
        if (this.additionalInfo != null) {
            return this.additionalInfo.stream()
                    .collect(Collectors.toMap(KeyValueRequest::getKey, KeyValueRequest::getValue));
        }
        return Collections.emptyMap();
    }
}

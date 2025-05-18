package br.com.wise.notification.domain;

import br.com.wise.notification.domain.enums.DeliveryMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class StreamMessage {
    private String notificationId;
    private DeliveryMethod deliveryMethod;
    private String message;
}

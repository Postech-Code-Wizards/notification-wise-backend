package br.com.wise.notification.domain;

import br.com.wise.notification.domain.enums.DeliveryMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@AllArgsConstructor
@ToString(of = {"notificationId", "deliveryMethod", "recipient", "message"})
public class StreamMessage {
    private String notificationId;
    private DeliveryMethod deliveryMethod;
    private String recipient;
    private String message;
}
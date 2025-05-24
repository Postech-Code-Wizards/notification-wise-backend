package br.com.wise.notification.application.usecase.notification;

import br.com.wise.notification.domain.NotificationMessage;
import br.com.wise.notification.domain.StreamMessage;
import br.com.wise.notification.gateway.notification.SendGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublishMessageUseCase {

    private final RetrieveSendGatewayUseCase retrieveSendGatewayUseCase;

    public void publishMessage(String notificationsId, NotificationMessage notificationMessage, String messageFormatted){
        var streamMessage = populate(notificationsId, notificationMessage, messageFormatted);
        SendGateway sendGateway = retrieveSendGatewayUseCase.execute(streamMessage);
        sendGateway.send(streamMessage);
    }

    private static StreamMessage populate(String notificationsId, NotificationMessage notificationMessage, String messageFormatted) {
        return StreamMessage.builder()
                .notificationId(notificationsId)
                .deliveryMethod(notificationMessage.getDeliveryMethod())
                .recipient(notificationMessage.getRecipient())
                .message(messageFormatted).build();
    }

}
package br.com.wise.notification.application.usecase.notification;

import br.com.wise.notification.domain.StreamMessage;
import br.com.wise.notification.gateway.notification.SendGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class RetrieveSendGatewayUseCase {

    private static final String DEFAULT_SENDING_GATEWAY_NAME = "send%sRabbitMQGateway";
    private final ApplicationContext applicationContext;

    public SendGateway execute(StreamMessage streamMessage) {
        String deliveryMethod = streamMessage.getDeliveryMethod().name().toLowerCase();
        String qualifierName = String.format(DEFAULT_SENDING_GATEWAY_NAME, StringUtils.capitalize(deliveryMethod));
        return (SendGateway) applicationContext.getBean(qualifierName);
    }

}
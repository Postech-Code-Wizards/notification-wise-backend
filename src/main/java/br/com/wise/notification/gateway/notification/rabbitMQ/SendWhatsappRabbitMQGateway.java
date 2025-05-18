package br.com.wise.notification.gateway.notification.rabbitMQ;

import br.com.wise.notification.domain.StreamMessage;
import br.com.wise.notification.gateway.notification.SendGateway;
import br.com.wise.notification.infrastructure.configurarion.NotificationConfig;
import br.com.wise.notification.infrastructure.configurarion.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Qualifier("sendWhatsappRabbitMQGateway")
public class SendWhatsappRabbitMQGateway implements SendGateway {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void send(StreamMessage message) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                NotificationConfig.WHATSAPP_ROUTING_KEY,
                message);
    }

}

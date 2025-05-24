package br.com.wise.notification.gateway.notification.rabbitmq;

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
@Qualifier("sendSmsRabbitMQGateway")
public class SendSmsRabbitMQGateway implements SendGateway {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void send(StreamMessage message) {

        log.info("[{}] Sending ID message to RabbitMQ: {}", this.getClass().getSimpleName(), message.getNotificationId());

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                NotificationConfig.SMS_ROUTING_KEY,
                message);
    }

}
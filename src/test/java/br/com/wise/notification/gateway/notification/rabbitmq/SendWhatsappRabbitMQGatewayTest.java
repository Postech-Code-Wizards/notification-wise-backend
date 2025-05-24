package br.com.wise.notification.gateway.notification.rabbitmq;

import br.com.wise.notification.domain.StreamMessage;
import br.com.wise.notification.infrastructure.configurarion.NotificationConfig;
import br.com.wise.notification.infrastructure.configurarion.RabbitMQConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.instancio.Instancio.create;
import static org.mockito.Mockito.verify;

class SendWhatsappRabbitMQGatewayTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private SendWhatsappRabbitMQGateway sendWhatsappRabbitMQGateway;

    @Test
    @DisplayName("Should send StreamMessage to RabbitMQ with WhatsApp routing key successfully")
    void shouldSendWhatsappMessageSuccessfully() {
        MockitoAnnotations.openMocks(this);
        StreamMessage message = create(StreamMessage.class);

        sendWhatsappRabbitMQGateway.send(message);

        verify(rabbitTemplate).convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                NotificationConfig.WHATSAPP_ROUTING_KEY,
                message
        );
    }
}
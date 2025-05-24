package br.com.wise.notification.application.usecase.notification;

import br.com.wise.notification.domain.StreamMessage;
import br.com.wise.notification.domain.enums.DeliveryMethod;
import br.com.wise.notification.gateway.notification.SendGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.context.ApplicationContext;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RetrieveSendGatewayUseCaseTest {

    @Mock
    private ApplicationContext applicationContext;

    private RetrieveSendGatewayUseCase retrieveSendGatewayUseCase;

    @BeforeEach
    void setUp() {
        applicationContext = mock(ApplicationContext.class);
        retrieveSendGatewayUseCase = new RetrieveSendGatewayUseCase(applicationContext);
    }

    @Test
    @DisplayName("Should retrieve the correct SendGateway bean for EMAIL delivery method")
    void shouldRetrieveSendGatewayForEmail() {
        StreamMessage streamMessage = Instancio.of(StreamMessage.class)
                .set(field(StreamMessage::getDeliveryMethod), DeliveryMethod.EMAIL)
                .create();

        SendGateway sendGatewayMock = mock(SendGateway.class);
        when(applicationContext.getBean("sendEmailRabbitMQGateway")).thenReturn(sendGatewayMock);

        SendGateway result = retrieveSendGatewayUseCase.execute(streamMessage);

        assertNotNull(result);
        verify(applicationContext).getBean("sendEmailRabbitMQGateway");
    }

    @Test
    @DisplayName("Should retrieve the correct SendGateway bean for SMS delivery method")
    void shouldRetrieveSendGatewayForSms() {
        StreamMessage streamMessage = Instancio.of(StreamMessage.class)
                .set(field(StreamMessage::getDeliveryMethod), DeliveryMethod.SMS)
                .create();

        SendGateway sendGatewayMock = mock(SendGateway.class);
        when(applicationContext.getBean("sendSmsRabbitMQGateway")).thenReturn(sendGatewayMock);

        SendGateway result = retrieveSendGatewayUseCase.execute(streamMessage);

        assertNotNull(result);
        verify(applicationContext).getBean("sendSmsRabbitMQGateway");
    }

    @Test
    @DisplayName("Should throw exception when no SendGateway bean is found")
    void shouldThrowExceptionWhenSendGatewayNotFound() {
        StreamMessage streamMessage = Instancio.of(StreamMessage.class)
                .set(field(StreamMessage::getDeliveryMethod), DeliveryMethod.WHATSAPP)
                .create();

        when(applicationContext.getBean("sendWhatsappRabbitMQGateway")).thenThrow(new RuntimeException("Bean not found"));

        assertThrows(RuntimeException.class, () -> retrieveSendGatewayUseCase.execute(streamMessage));
        verify(applicationContext).getBean("sendWhatsappRabbitMQGateway");
    }
}
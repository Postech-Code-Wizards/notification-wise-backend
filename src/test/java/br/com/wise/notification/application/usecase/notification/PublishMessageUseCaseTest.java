package br.com.wise.notification.application.usecase.notification;

import br.com.wise.notification.domain.NotificationMessage;
import br.com.wise.notification.domain.StreamMessage;
import br.com.wise.notification.gateway.notification.SendGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PublishMessageUseCaseTest {

    @Mock
    private RetrieveSendGatewayUseCase retrieveSendGatewayUseCase;

    @InjectMocks
    private PublishMessageUseCase publishMessageUseCase;

    @Mock
    private SendGateway sendGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should publish message successfully")
    void testPublishMessageSuccess() {
        String notificationsId = Instancio.create(String.class);
        NotificationMessage notificationMessage = Instancio.create(NotificationMessage.class);
        String messageFormatted = "Formatted message";

        when(retrieveSendGatewayUseCase.execute(any(StreamMessage.class))).thenReturn(sendGateway);

        publishMessageUseCase.publishMessage(notificationsId, notificationMessage, messageFormatted);

        verify(retrieveSendGatewayUseCase).execute(any(StreamMessage.class));
        verify(sendGateway).send(any(StreamMessage.class));
    }

}
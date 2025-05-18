package br.com.wise.notification.application.facade;

import br.com.wise.notification.application.facade.converter.NotificationRequestToDomain;
import br.com.wise.notification.application.usecase.consumer.EmailConsumerUseCase;
import br.com.wise.notification.application.usecase.consumer.SmsConsumerUseCase;
import br.com.wise.notification.application.usecase.consumer.WhatsAppConsumerUseCase;
import br.com.wise.notification.application.usecase.notification.SendNotificationUseCase;
import br.com.wise.notification.domain.NotificationMessage;
import br.com.wise.notification.domain.StreamMessage;
import br.com.wise.notification.infrastructure.controller.dtos.request.NotificationRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class NotificationFacadeTest {

    @Mock
    private NotificationRequestToDomain notificationRequestToDomain;

    @Mock
    private SendNotificationUseCase sendNotificationUseCase;

    @Mock
    private EmailConsumerUseCase emailConsumerUseCase;

    @Mock
    private SmsConsumerUseCase smsConsumerUseCase;

    @Mock
    private WhatsAppConsumerUseCase whatsAppConsumerUseCase;

    @InjectMocks
    private NotificationFacade notificationFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should send a notification successfully")
    void testSendNotification() {
        NotificationRequest notificationRequest = Instancio.create(NotificationRequest.class);
        NotificationMessage notificationMessage = Instancio.create(NotificationMessage.class);

        when(notificationRequestToDomain.convert(notificationRequest)).thenReturn(notificationMessage);
        when(sendNotificationUseCase.execute(notificationMessage)).thenReturn(true);

        boolean result = notificationFacade.sendNotification(notificationRequest);

        assertTrue(result);
        verify(notificationRequestToDomain).convert(notificationRequest);
        verify(sendNotificationUseCase).execute(notificationMessage);
    }

    @Test
    @DisplayName("Should process email listener successfully")
    void testEmailListener() {
        StreamMessage message = Instancio.create(StreamMessage.class);

        notificationFacade.emailListener(message);

        verify(emailConsumerUseCase).execute(message);
    }

    @Test
    @DisplayName("Should process SMS listener successfully")
    void testSmsListener() {
        StreamMessage message = Instancio.create(StreamMessage.class);

        notificationFacade.smsListener(message);

        verify(smsConsumerUseCase).execute(message);
    }

    @Test
    @DisplayName("Should process WhatsApp listener successfully")
    void testWhatsAppListener() {
        StreamMessage message = Instancio.create(StreamMessage.class);

        notificationFacade.whatsappListener(message);

        verify(whatsAppConsumerUseCase).execute(message);
    }
}
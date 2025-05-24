package br.com.wise.notification.application.usecase.consumer;

import br.com.wise.notification.application.usecase.notification.UpdateStatusNotificationUseCase;
import br.com.wise.notification.domain.StreamMessage;
import br.com.wise.notification.domain.enums.StatusNotification;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

class WhatsAppConsumerUseCaseTest {

    @Mock
    private UpdateStatusNotificationUseCase updateStatusNotificationUseCase;

    @InjectMocks
    private WhatsAppConsumerUseCase whatsAppConsumerUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should process WhatsApp message and update status to SENT successfully")
    void testExecuteSuccess() {
        StreamMessage message = Instancio.create(StreamMessage.class);

        whatsAppConsumerUseCase.execute(message);

        verify(updateStatusNotificationUseCase).execute(message.getNotificationId(), StatusNotification.SENT);
    }

    @Test
    @DisplayName("Should handle exception and update status to FAILURE")
    void testExecuteFailure() {
        StreamMessage message = Instancio.create(StreamMessage.class);
        doThrow(new RuntimeException("Test exception")).when(updateStatusNotificationUseCase)
                .execute(message.getNotificationId(), StatusNotification.SENT);

        whatsAppConsumerUseCase.execute(message);

        verify(updateStatusNotificationUseCase).execute(message.getNotificationId(), StatusNotification.FAILURE);
    }
}
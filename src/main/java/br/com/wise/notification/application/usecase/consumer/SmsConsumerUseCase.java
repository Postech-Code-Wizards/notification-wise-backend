package br.com.wise.notification.application.usecase.consumer;

import br.com.wise.notification.application.usecase.notification.UpdateStatusNotificationUseCase;
import br.com.wise.notification.domain.StreamMessage;
import br.com.wise.notification.domain.enums.StatusNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SmsConsumerUseCase {

    private final UpdateStatusNotificationUseCase updateStatusNotificationUseCase;

    public void execute(StreamMessage message) {
        try {

            log.info("[{}] Received message of SMS: {}", this.getClass().getSimpleName(), message.toString());
            updateStatusNotificationUseCase.execute(message.getNotificationId(), StatusNotification.SENT);
            log.info("[{}] Message '{}' sent to recipient '{}'", this.getClass().getSimpleName(), message.getMessage(), message.getRecipient());

        } catch (Exception e) {
            log.error("[{}] Error processing message of SMS: {}", this.getClass().getSimpleName(), e.getMessage());
            updateStatusNotificationUseCase.execute(message.getNotificationId(), StatusNotification.FAILURE);
        }
    }

}
package br.com.wise.notification.application.usecase.consumer;

import br.com.wise.notification.domain.StreamMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailConsumerUseCase {

    public void execute(StreamMessage message) {
        System.out.println("---> " + message.getMessage());

    }

}

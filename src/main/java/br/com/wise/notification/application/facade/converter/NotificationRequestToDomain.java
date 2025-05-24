package br.com.wise.notification.application.facade.converter;

import br.com.wise.notification.domain.NotificationMessage;
import br.com.wise.notification.infrastructure.controller.dtos.request.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationRequestToDomain {

    public NotificationMessage convert(NotificationRequest notificationRequest){
        return NotificationMessage.builder()
                .templateName(notificationRequest.getTemplateName())
                .patientId(notificationRequest.getPatientId())
                .recipient(notificationRequest.getRecipient())
                .deliveryMethod(notificationRequest.getDeliveryMethod())
                .additionalInfo(notificationRequest.getAdditionalInfoMap())
                .build();
    }
}
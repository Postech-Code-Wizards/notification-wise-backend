package br.com.wise.notification.application.facade;

import br.com.wise.notification.application.facade.converter.NotificationTemplatesDomainToResponse;
import br.com.wise.notification.application.facade.converter.NotificationTemplatesRequestToDomain;
import br.com.wise.notification.application.usecase.CreateNotificationTemplatesUseCase;
import br.com.wise.notification.domain.NotificationTemplates;
import br.com.wise.notification.infrastructure.controller.dtos.request.NotificationTemplatesRequest;
import br.com.wise.notification.infrastructure.controller.dtos.response.NotificationTemplatesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationTemplatesFacade {

    private final NotificationTemplatesRequestToDomain notificationTemplatesRequestToDomain;
    private final NotificationTemplatesDomainToResponse notificationTemplatesDomainToResponse;
    private final CreateNotificationTemplatesUseCase createNotificationTemplatesUseCase;

    public NotificationTemplatesResponse create (NotificationTemplatesRequest notificationTemplatesRequest) {
        NotificationTemplates notificationTemplates = notificationTemplatesRequestToDomain.convert(notificationTemplatesRequest);
        NotificationTemplates savedNotificationTemplates = createNotificationTemplatesUseCase.execute(notificationTemplates);
        return notificationTemplatesDomainToResponse.convert(savedNotificationTemplates);
    }
}

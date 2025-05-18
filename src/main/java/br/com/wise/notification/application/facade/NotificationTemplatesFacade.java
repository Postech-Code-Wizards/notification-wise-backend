package br.com.wise.notification.application.facade;

import br.com.wise.notification.application.facade.converter.NotificationTemplatesDomainToResponse;
import br.com.wise.notification.application.facade.converter.NotificationTemplatesRequestToDomain;
import br.com.wise.notification.application.usecase.notificationTemplates.CreateNotificationTemplatesUseCase;
import br.com.wise.notification.application.usecase.notificationTemplates.DeleteNotificationTemplatesUseCase;
import br.com.wise.notification.application.usecase.notificationTemplates.RetrieveNotificationTemplatesAllUseCase;
import br.com.wise.notification.application.usecase.notificationTemplates.RetrieveNotificationTemplatesByIdUseCase;
import br.com.wise.notification.application.usecase.notificationTemplates.RetrieveNotificationTemplatesByNameUseCase;
import br.com.wise.notification.infrastructure.controller.dtos.response.NotificationTemplatesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationTemplatesFacade {

    private final NotificationTemplatesRequestToDomain notificationTemplatesRequestToDomain;
    private final NotificationTemplatesDomainToResponse notificationTemplatesDomainToResponse;
    private final CreateNotificationTemplatesUseCase createNotificationTemplatesUseCase;
    private final RetrieveNotificationTemplatesByIdUseCase retrieveNotificationTemplatesByIdUseCase;
    private final RetrieveNotificationTemplatesByNameUseCase retrieveNotificationTemplatesByNameUseCase;
    private final DeleteNotificationTemplatesUseCase deleteNotificationTemplatesUseCase;
    private final RetrieveNotificationTemplatesAllUseCase retrieveNotificationTemplatesAllUseCase;

    public NotificationTemplatesResponse create(String name, String message) {
        var notificationTemplates = notificationTemplatesRequestToDomain.convert(name, message);
        var savedNotificationTemplates = createNotificationTemplatesUseCase.execute(notificationTemplates);
        return notificationTemplatesDomainToResponse.convert(savedNotificationTemplates);
    }

    public NotificationTemplatesResponse update(String id, String name, String message) {
        var retrieveNotificationTemplates = retrieveNotificationTemplatesByIdUseCase.execute(id);
        var notificationTemplates = notificationTemplatesRequestToDomain.convert(id, name, message, retrieveNotificationTemplates.getCreatedAt());
        var savedNotificationTemplates = createNotificationTemplatesUseCase.execute(notificationTemplates);
        return notificationTemplatesDomainToResponse.convert(savedNotificationTemplates);
    }

    public boolean delete(String id){
        return deleteNotificationTemplatesUseCase.execute(id);
    }

    public NotificationTemplatesResponse getById(String id){
        var notificationTemplates = retrieveNotificationTemplatesByIdUseCase.execute(id);
        return notificationTemplatesDomainToResponse.convert(notificationTemplates);
    }

    public NotificationTemplatesResponse getByName(String name){
        var notificationTemplates = retrieveNotificationTemplatesByNameUseCase.execute(name);
        return notificationTemplatesDomainToResponse.convert(notificationTemplates);
    }

    public List<NotificationTemplatesResponse> listAll(){
        var notificationTemplatesList = retrieveNotificationTemplatesAllUseCase.execute();
        return notificationTemplatesList.stream().map(notificationTemplatesDomainToResponse::convert).toList();
    }

}

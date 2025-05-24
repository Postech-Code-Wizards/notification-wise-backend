package br.com.wise.notification.application.facade;

import br.com.wise.notification.application.facade.converter.NotificationTemplatesDomainToResponse;
import br.com.wise.notification.application.facade.converter.CreateNotificationTemplatesRequestToDomain;
import br.com.wise.notification.application.facade.converter.UpdateNotificationTemplatesRequestToDomain;
import br.com.wise.notification.application.usecase.notificationtemplates.CreateNotificationTemplatesUseCase;
import br.com.wise.notification.application.usecase.notificationtemplates.DeleteNotificationTemplatesUseCase;
import br.com.wise.notification.application.usecase.notificationtemplates.RetrieveNotificationTemplatesAllUseCase;
import br.com.wise.notification.application.usecase.notificationtemplates.RetrieveNotificationTemplatesByIdUseCase;
import br.com.wise.notification.application.usecase.notificationtemplates.RetrieveNotificationTemplatesByNameUseCase;
import br.com.wise.notification.infrastructure.controller.dtos.request.CreateNotificationTemplatesRequest;
import br.com.wise.notification.infrastructure.controller.dtos.request.UpdateNotificationTemplatesRequest;
import br.com.wise.notification.infrastructure.controller.dtos.response.NotificationTemplatesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationTemplatesFacade {

    private final CreateNotificationTemplatesRequestToDomain createNotificationTemplatesRequestToDomain;
    private final UpdateNotificationTemplatesRequestToDomain updateNotificationTemplatesRequestToDomain;
    private final NotificationTemplatesDomainToResponse notificationTemplatesDomainToResponse;
    private final CreateNotificationTemplatesUseCase createNotificationTemplatesUseCase;
    private final RetrieveNotificationTemplatesByIdUseCase retrieveNotificationTemplatesByIdUseCase;
    private final RetrieveNotificationTemplatesByNameUseCase retrieveNotificationTemplatesByNameUseCase;
    private final DeleteNotificationTemplatesUseCase deleteNotificationTemplatesUseCase;
    private final RetrieveNotificationTemplatesAllUseCase retrieveNotificationTemplatesAllUseCase;

    public NotificationTemplatesResponse create(CreateNotificationTemplatesRequest createNotificationTemplatesRequest) {
        var notificationTemplates = createNotificationTemplatesRequestToDomain.convert(createNotificationTemplatesRequest);
        var savedNotificationTemplates = createNotificationTemplatesUseCase.execute(notificationTemplates);
        return notificationTemplatesDomainToResponse.convert(savedNotificationTemplates);
    }

    public NotificationTemplatesResponse update(UpdateNotificationTemplatesRequest updateNotificationTemplatesRequest) {
        var retrieveNotificationTemplates = retrieveNotificationTemplatesByIdUseCase.execute(updateNotificationTemplatesRequest.getId());
        var notificationTemplates = updateNotificationTemplatesRequestToDomain.convert(updateNotificationTemplatesRequest, retrieveNotificationTemplates.getCreatedAt());
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

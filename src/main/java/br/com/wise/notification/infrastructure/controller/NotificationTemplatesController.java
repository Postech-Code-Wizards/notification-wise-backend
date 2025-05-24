package br.com.wise.notification.infrastructure.controller;

import br.com.wise.notification.application.facade.NotificationTemplatesFacade;
import br.com.wise.notification.infrastructure.controller.dtos.request.CreateNotificationTemplatesRequest;
import br.com.wise.notification.infrastructure.controller.dtos.request.UpdateNotificationTemplatesRequest;
import br.com.wise.notification.infrastructure.controller.dtos.response.NotificationTemplatesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NotificationTemplatesController {

    private final NotificationTemplatesFacade notificationTemplatesFacade;

    @MutationMapping
    public NotificationTemplatesResponse createNotificationTemplate(@Argument("createNotificationTemplatesRequest") CreateNotificationTemplatesRequest createNotificationTemplatesRequest) {
        return notificationTemplatesFacade.create(createNotificationTemplatesRequest);
    }

    @MutationMapping
    public NotificationTemplatesResponse updateNotificationTemplate(@Argument("updateNotificationTemplatesRequest") UpdateNotificationTemplatesRequest updateNotificationTemplatesRequest) {
        return notificationTemplatesFacade.update(updateNotificationTemplatesRequest);
    }

    @MutationMapping
    public Boolean deleteNotificationTemplate(@Argument String id) {
        return notificationTemplatesFacade.delete(id);
    }

    @QueryMapping
    public NotificationTemplatesResponse getNotificationTemplatesById(@Argument String id) {
        return notificationTemplatesFacade.getById(id);
    }

    @QueryMapping
    public NotificationTemplatesResponse getNotificationTemplatesByName(@Argument String name) {
        return notificationTemplatesFacade.getByName(name);
    }

    @QueryMapping
    public List<NotificationTemplatesResponse> listAllNotificationTemplates() {
        return notificationTemplatesFacade.listAll();
    }

}
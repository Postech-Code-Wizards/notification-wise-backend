package br.com.wise.notification.infrastructure.controller;

import br.com.wise.notification.application.facade.NotificationTemplatesFacade;
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
    public NotificationTemplatesResponse createNotificationTemplate(@Argument String name, @Argument String message) {
        return notificationTemplatesFacade.create(name, message);
    }

    @MutationMapping
    public NotificationTemplatesResponse updateNotificationTemplate(@Argument String id,@Argument String name, @Argument String message) {
        return notificationTemplatesFacade.update(id, name, message);
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
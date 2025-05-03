package br.com.wise.notification.infrastructure.controller;

import br.com.wise.notification.application.facade.NotificationTemplatesFacade;
import br.com.wise.notification.infrastructure.controller.dtos.request.NotificationTemplatesRequest;
import br.com.wise.notification.infrastructure.controller.dtos.response.NotificationTemplatesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class NotificationTemplatesController {

    private final NotificationTemplatesFacade notificationTemplatesFacade;

    @MutationMapping
    public NotificationTemplatesResponse createNotificationTemplate(@Argument String name, @Argument String message) {
        NotificationTemplatesRequest notificationTemplatesRequest = new NotificationTemplatesRequest(name, message);
        return notificationTemplatesFacade.create(notificationTemplatesRequest);
    }

    @QueryMapping
    public NotificationTemplatesResponse getNotificationById(@Argument String id) {
        //NotificationTemplatesRequest notificationTemplatesRequest = new NotificationTemplatesRequest(name, message);
        //return notificationTemplatesFacade.create(notificationTemplatesRequest);
        return new NotificationTemplatesResponse();
    }
}

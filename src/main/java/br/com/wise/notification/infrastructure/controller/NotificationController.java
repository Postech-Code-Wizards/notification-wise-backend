package br.com.wise.notification.infrastructure.controller;

import br.com.wise.notification.application.facade.NotificationFacade;
import br.com.wise.notification.infrastructure.controller.dtos.request.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationFacade notificationFacade;

    @MutationMapping
    public Boolean sendNotification(@Argument("notificationRequest") NotificationRequest notificationRequest) {
        return notificationFacade.sendNotification(notificationRequest);
    }

}

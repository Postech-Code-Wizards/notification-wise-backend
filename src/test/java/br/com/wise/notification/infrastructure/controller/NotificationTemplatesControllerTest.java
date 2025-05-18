package br.com.wise.notification.infrastructure.controller;

import br.com.wise.notification.application.facade.NotificationTemplatesFacade;
import br.com.wise.notification.infrastructure.controller.dtos.request.CreateNotificationTemplatesRequest;
import br.com.wise.notification.infrastructure.controller.dtos.request.UpdateNotificationTemplatesRequest;
import br.com.wise.notification.infrastructure.controller.dtos.response.NotificationTemplatesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.instancio.Instancio.create;
import static org.instancio.Instancio.createList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class NotificationTemplatesControllerTest {

    @Mock
    private NotificationTemplatesFacade notificationTemplatesFacade;

    @InjectMocks
    private NotificationTemplatesController notificationTemplatesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create a notification template successfully")
    void shouldCreateNotificationTemplateSuccessfully() {
        CreateNotificationTemplatesRequest request = create(CreateNotificationTemplatesRequest.class);
        NotificationTemplatesResponse response = create(NotificationTemplatesResponse.class);

        when(notificationTemplatesFacade.create(request)).thenReturn(response);

        NotificationTemplatesResponse result = notificationTemplatesController.createNotificationTemplate(request);

        assertEquals(response, result);
        verify(notificationTemplatesFacade).create(request);
    }

    @Test
    @DisplayName("Should update a notification template successfully")
    void shouldUpdateNotificationTemplateSuccessfully() {
        UpdateNotificationTemplatesRequest request = create(UpdateNotificationTemplatesRequest.class);
        NotificationTemplatesResponse response = create(NotificationTemplatesResponse.class);

        when(notificationTemplatesFacade.update(request)).thenReturn(response);

        NotificationTemplatesResponse result = notificationTemplatesController.updateNotificationTemplate(request);

        assertEquals(response, result);
        verify(notificationTemplatesFacade).update(request);
    }

    @Test
    @DisplayName("Should delete a notification template successfully")
    void shouldDeleteNotificationTemplateSuccessfully() {
        String id = create(String.class);

        when(notificationTemplatesFacade.delete(id)).thenReturn(true);

        Boolean result = notificationTemplatesController.deleteNotificationTemplate(id);

        assertEquals(true, result);
        verify(notificationTemplatesFacade).delete(id);
    }

    @Test
    @DisplayName("Should get a notification template by ID successfully")
    void shouldGetNotificationTemplateByIdSuccessfully() {
        String id = create(String.class);
        NotificationTemplatesResponse response = create(NotificationTemplatesResponse.class);

        when(notificationTemplatesFacade.getById(id)).thenReturn(response);

        NotificationTemplatesResponse result = notificationTemplatesController.getNotificationTemplatesById(id);

        assertEquals(response, result);
        verify(notificationTemplatesFacade).getById(id);
    }

    @Test
    @DisplayName("Should get a notification template by name successfully")
    void shouldGetNotificationTemplateByNameSuccessfully() {
        String name = create(String.class);
        NotificationTemplatesResponse response = create(NotificationTemplatesResponse.class);

        when(notificationTemplatesFacade.getByName(name)).thenReturn(response);

        NotificationTemplatesResponse result = notificationTemplatesController.getNotificationTemplatesByName(name);

        assertEquals(response, result);
        verify(notificationTemplatesFacade).getByName(name);
    }

    @Test
    @DisplayName("Should list all notification templates successfully")
    void shouldListAllNotificationTemplatesSuccessfully() {
        List<NotificationTemplatesResponse> responses = createList(NotificationTemplatesResponse.class);

        when(notificationTemplatesFacade.listAll()).thenReturn(responses);

        List<NotificationTemplatesResponse> result = notificationTemplatesController.listAllNotificationTemplates();

        assertEquals(responses, result);
        verify(notificationTemplatesFacade).listAll();
    }
}
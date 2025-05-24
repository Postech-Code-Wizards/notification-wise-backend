package br.com.wise.notification.application.facade;

import br.com.wise.notification.application.facade.converter.CreateNotificationTemplatesRequestToDomain;
import br.com.wise.notification.application.facade.converter.NotificationTemplatesDomainToResponse;
import br.com.wise.notification.application.facade.converter.UpdateNotificationTemplatesRequestToDomain;
import br.com.wise.notification.application.usecase.notificationtemplates.CreateNotificationTemplatesUseCase;
import br.com.wise.notification.application.usecase.notificationtemplates.DeleteNotificationTemplatesUseCase;
import br.com.wise.notification.application.usecase.notificationtemplates.RetrieveNotificationTemplatesAllUseCase;
import br.com.wise.notification.application.usecase.notificationtemplates.RetrieveNotificationTemplatesByIdUseCase;
import br.com.wise.notification.application.usecase.notificationtemplates.RetrieveNotificationTemplatesByNameUseCase;
import br.com.wise.notification.domain.NotificationTemplates;
import br.com.wise.notification.infrastructure.controller.dtos.request.CreateNotificationTemplatesRequest;
import br.com.wise.notification.infrastructure.controller.dtos.request.UpdateNotificationTemplatesRequest;
import br.com.wise.notification.infrastructure.controller.dtos.response.NotificationTemplatesResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class NotificationTemplatesFacadeTest {

    @Mock
    private CreateNotificationTemplatesRequestToDomain createNotificationTemplatesRequestToDomain;

    @Mock
    private UpdateNotificationTemplatesRequestToDomain updateNotificationTemplatesRequestToDomain;

    @Mock
    private NotificationTemplatesDomainToResponse notificationTemplatesDomainToResponse;

    @Mock
    private CreateNotificationTemplatesUseCase createNotificationTemplatesUseCase;

    @Mock
    private RetrieveNotificationTemplatesByIdUseCase retrieveNotificationTemplatesByIdUseCase;

    @Mock
    private RetrieveNotificationTemplatesByNameUseCase retrieveNotificationTemplatesByNameUseCase;

    @Mock
    private DeleteNotificationTemplatesUseCase deleteNotificationTemplatesUseCase;

    @Mock
    private RetrieveNotificationTemplatesAllUseCase retrieveNotificationTemplatesAllUseCase;

    @InjectMocks
    private NotificationTemplatesFacade notificationTemplatesFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create a notification template successfully")
    void testCreateNotificationTemplate() {
        CreateNotificationTemplatesRequest createNotificationTemplatesRequest = Instancio.create(CreateNotificationTemplatesRequest.class);
        NotificationTemplates notificationTemplates = Instancio.create(NotificationTemplates.class);
        NotificationTemplates savedNotificationTemplates = Instancio.create(NotificationTemplates.class);
        NotificationTemplatesResponse response = Instancio.create(NotificationTemplatesResponse.class);

        when(createNotificationTemplatesRequestToDomain.convert(createNotificationTemplatesRequest)).thenReturn(notificationTemplates);
        when(createNotificationTemplatesUseCase.execute(notificationTemplates)).thenReturn(savedNotificationTemplates);
        when(notificationTemplatesDomainToResponse.convert(savedNotificationTemplates)).thenReturn(response);

        NotificationTemplatesResponse result = notificationTemplatesFacade.create(createNotificationTemplatesRequest);

        assertEquals(response, result);
        verify(createNotificationTemplatesRequestToDomain).convert(createNotificationTemplatesRequest);
        verify(createNotificationTemplatesUseCase).execute(notificationTemplates);
        verify(notificationTemplatesDomainToResponse).convert(savedNotificationTemplates);
    }

    @Test
    @DisplayName("Should update a notification template successfully")
    void testUpdateNotificationTemplate() {
        UpdateNotificationTemplatesRequest updateNotificationTemplatesRequest = Instancio.create(UpdateNotificationTemplatesRequest.class);
        NotificationTemplates existingNotificationTemplates = Instancio.create(NotificationTemplates.class);
        NotificationTemplates updatedNotificationTemplates = Instancio.create(NotificationTemplates.class);
        NotificationTemplatesResponse response = Instancio.create(NotificationTemplatesResponse.class);

        when(retrieveNotificationTemplatesByIdUseCase.execute(updateNotificationTemplatesRequest.getId())).thenReturn(existingNotificationTemplates);
        when(updateNotificationTemplatesRequestToDomain.convert(updateNotificationTemplatesRequest, existingNotificationTemplates.getCreatedAt()))
                .thenReturn(updatedNotificationTemplates);
        when(createNotificationTemplatesUseCase.execute(updatedNotificationTemplates)).thenReturn(updatedNotificationTemplates);
        when(notificationTemplatesDomainToResponse.convert(updatedNotificationTemplates)).thenReturn(response);

        NotificationTemplatesResponse result = notificationTemplatesFacade.update(updateNotificationTemplatesRequest);

        assertEquals(response, result);
        verify(retrieveNotificationTemplatesByIdUseCase).execute(updateNotificationTemplatesRequest.getId());
        verify(updateNotificationTemplatesRequestToDomain).convert(updateNotificationTemplatesRequest, existingNotificationTemplates.getCreatedAt());
        verify(createNotificationTemplatesUseCase).execute(updatedNotificationTemplates);
        verify(notificationTemplatesDomainToResponse).convert(updatedNotificationTemplates);
    }

    @Test
    @DisplayName("Should delete a notification template successfully")
    void testDeleteNotificationTemplate() {
        String id = "Test ID";

        when(deleteNotificationTemplatesUseCase.execute(id)).thenReturn(true);

        boolean result = notificationTemplatesFacade.delete(id);

        assertTrue(result);
        verify(deleteNotificationTemplatesUseCase).execute(id);
    }

    @Test
    @DisplayName("Should retrieve a notification template by ID successfully")
    void testGetNotificationTemplateById() {
        String id = "Test ID";
        NotificationTemplates notificationTemplates = Instancio.create(NotificationTemplates.class);
        NotificationTemplatesResponse response = Instancio.create(NotificationTemplatesResponse.class);

        when(retrieveNotificationTemplatesByIdUseCase.execute(id)).thenReturn(notificationTemplates);
        when(notificationTemplatesDomainToResponse.convert(notificationTemplates)).thenReturn(response);

        NotificationTemplatesResponse result = notificationTemplatesFacade.getById(id);

        assertEquals(response, result);
        verify(retrieveNotificationTemplatesByIdUseCase).execute(id);
        verify(notificationTemplatesDomainToResponse).convert(notificationTemplates);
    }

    @Test
    @DisplayName("Should retrieve a notification template by name successfully")
    void testGetNotificationTemplateByName() {
        String name = "Test Name";
        NotificationTemplates notificationTemplates = Instancio.create(NotificationTemplates.class);
        NotificationTemplatesResponse response = Instancio.create(NotificationTemplatesResponse.class);

        when(retrieveNotificationTemplatesByNameUseCase.execute(name)).thenReturn(notificationTemplates);
        when(notificationTemplatesDomainToResponse.convert(notificationTemplates)).thenReturn(response);

        NotificationTemplatesResponse result = notificationTemplatesFacade.getByName(name);

        assertEquals(response, result);
        verify(retrieveNotificationTemplatesByNameUseCase).execute(name);
        verify(notificationTemplatesDomainToResponse).convert(notificationTemplates);
    }

    @Test
    @DisplayName("Should retrieve all notification templates successfully")
    void testListAllNotificationTemplates() {
        List<NotificationTemplates> notificationTemplatesList = Instancio.ofList(NotificationTemplates.class).size(3).create();
        List<NotificationTemplatesResponse> responseList = Instancio.ofList(NotificationTemplatesResponse.class).size(3).create();

        when(retrieveNotificationTemplatesAllUseCase.execute()).thenReturn(notificationTemplatesList);
        when(notificationTemplatesDomainToResponse.convert(any(NotificationTemplates.class)))
                .thenAnswer(invocation -> {
                    NotificationTemplates arg = invocation.getArgument(0);
                    return responseList.get(notificationTemplatesList.indexOf(arg));
                });

        List<NotificationTemplatesResponse> result = notificationTemplatesFacade.listAll();

        assertEquals(responseList, result);
        verify(retrieveNotificationTemplatesAllUseCase).execute();
        verify(notificationTemplatesDomainToResponse, times(notificationTemplatesList.size())).convert(any(NotificationTemplates.class));
    }
}
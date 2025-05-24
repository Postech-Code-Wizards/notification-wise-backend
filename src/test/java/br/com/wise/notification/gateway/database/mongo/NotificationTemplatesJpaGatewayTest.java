package br.com.wise.notification.gateway.database.mongo;

import br.com.wise.notification.domain.NotificationTemplates;
import br.com.wise.notification.gateway.database.mongo.converter.NotificationTemplatesDomainToEntityConverter;
import br.com.wise.notification.gateway.database.mongo.converter.NotificationTemplatesEntityToDomainConverter;
import br.com.wise.notification.gateway.database.mongo.entities.NotificationTemplatesEntity;
import br.com.wise.notification.gateway.database.mongo.repositories.NotificationTemplatesRepository;
import br.com.wise.notification.infrastructure.controller.exception.BusinessException;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.instancio.Instancio.create;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class NotificationTemplatesJpaGatewayTest {

    @Mock
    private NotificationTemplatesRepository notificationTemplatesRepository;

    @Mock
    private NotificationTemplatesDomainToEntityConverter domainToEntityConverter;

    @Mock
    private NotificationTemplatesEntityToDomainConverter entityToDomainConverter;

    @InjectMocks
    private NotificationTemplatesJpaGateway notificationTemplatesJpaGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should find notification template by ID successfully")
    void shouldFindByIdSuccessfully() {
        String id = Instancio.create(String.class);
        var entity = Instancio.create(NotificationTemplatesEntity.class);
        var domain = Instancio.create(NotificationTemplates.class);

        when(notificationTemplatesRepository.findById(id)).thenReturn(Optional.of(entity));
        when(entityToDomainConverter.convert(entity)).thenReturn(domain);

        NotificationTemplates result = notificationTemplatesJpaGateway.findById(id);

        assertEquals(domain, result);
    }

    @Test
    @DisplayName("Should throw exception when notification template not found by ID")
    void shouldThrowExceptionWhenFindByIdFails() {
        String id = Instancio.create(String.class);

        when(notificationTemplatesRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> notificationTemplatesJpaGateway.findById(id));
    }

    @Test
    @DisplayName("Should find notification template by name successfully")
    void shouldFindByNameSuccessfully() {
        String name = Instancio.create(String.class);
        var entity = Instancio.create(NotificationTemplatesEntity.class);
        var domain = Instancio.create(NotificationTemplates.class);

        when(notificationTemplatesRepository.findByName(name)).thenReturn(Optional.of(entity));
        when(entityToDomainConverter.convert(entity)).thenReturn(domain);

        NotificationTemplates result = notificationTemplatesJpaGateway.findByName(name);

        assertEquals(domain, result);
    }

    @Test
    @DisplayName("Should throw exception when notification template not found by name")
    void shouldThrowExceptionWhenFindByNameFails() {
        String name = Instancio.create(String.class);

        when(notificationTemplatesRepository.findByName(name)).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> notificationTemplatesJpaGateway.findByName(name));
    }

    @Test
    @DisplayName("Should find all notification templates successfully")
    void shouldFindAllSuccessfully() {
        var entities = List.of(Instancio.create(NotificationTemplatesEntity.class), create(NotificationTemplatesEntity.class));
        var domains = List.of(Instancio.create(NotificationTemplates.class), Instancio.create(NotificationTemplates.class));

        when(notificationTemplatesRepository.findAll()).thenReturn(entities);
        when(entityToDomainConverter.convert(any())).thenReturn(domains.get(0), domains.get(1));

        List<NotificationTemplates> result = notificationTemplatesJpaGateway.findAll();

        assertEquals(domains, result);
    }

    @Test
    @DisplayName("Should save notification template successfully")
    void shouldSaveSuccessfully() {
        var domain = Instancio.create(NotificationTemplates.class);
        var entity = Instancio.create(NotificationTemplatesEntity.class);
        var savedEntity = Instancio.create(NotificationTemplatesEntity.class);
        var savedDomain = Instancio.create(NotificationTemplates.class);

        when(domainToEntityConverter.convert(domain)).thenReturn(entity);
        when(notificationTemplatesRepository.save(entity)).thenReturn(savedEntity);
        when(entityToDomainConverter.convert(savedEntity)).thenReturn(savedDomain);

        NotificationTemplates result = notificationTemplatesJpaGateway.save(domain);

        assertEquals(savedDomain, result);
    }

    @Test
    @DisplayName("Should update notification template successfully")
    void shouldUpdateSuccessfully() {
        var domain = Instancio.create(NotificationTemplates.class);
        var entity = Instancio.create(NotificationTemplatesEntity.class);
        var updatedEntity = Instancio.create(NotificationTemplatesEntity.class);
        var updatedDomain = Instancio.create(NotificationTemplates.class);

        when(notificationTemplatesRepository.findById(domain.getId())).thenReturn(Optional.of(entity));
        when(domainToEntityConverter.convert(domain)).thenReturn(entity);
        when(notificationTemplatesRepository.save(entity)).thenReturn(updatedEntity);
        when(entityToDomainConverter.convert(updatedEntity)).thenReturn(updatedDomain);

        NotificationTemplates result = notificationTemplatesJpaGateway.update(domain);

        assertEquals(updatedDomain, result);
    }

    @Test
    @DisplayName("Should throw exception when updating non-existent notification template")
    void shouldThrowExceptionWhenUpdateFails() {
        var domain = create(NotificationTemplates.class);

        when(notificationTemplatesRepository.findById(domain.getId())).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> notificationTemplatesJpaGateway.update(domain));
    }

    @Test
    @DisplayName("Should delete notification template successfully")
    void shouldDeleteSuccessfully() {
        String id = create(String.class);

        boolean result = notificationTemplatesJpaGateway.delete(id);

        verify(notificationTemplatesRepository).deleteById(id);
        assertEquals(true, result);
    }
}
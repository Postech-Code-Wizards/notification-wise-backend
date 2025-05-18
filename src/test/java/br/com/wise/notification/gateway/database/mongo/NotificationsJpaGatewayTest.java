package br.com.wise.notification.gateway.database.mongo;

import br.com.wise.notification.domain.Notifications;
import br.com.wise.notification.gateway.database.mongo.converter.NotificationsDomainToEntityConverter;
import br.com.wise.notification.gateway.database.mongo.converter.NotificationsEntityToDomainConverter;
import br.com.wise.notification.gateway.database.mongo.entities.NotificationsEntity;
import br.com.wise.notification.gateway.database.mongo.repositories.NotificationsRepository;
import br.com.wise.notification.infrastructure.controller.exception.BusinessException;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class NotificationsJpaGatewayTest {

    @Mock
    private NotificationsRepository notificationsRepository;

    @Mock
    private NotificationsDomainToEntityConverter domainToEntityConverter;

    @Mock
    private NotificationsEntityToDomainConverter entityToDomainConverter;

    @InjectMocks
    private NotificationsJpaGateway notificationsJpaGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should find notification by ID successfully")
    void shouldFindByIdSuccessfully() {
        String id = Instancio.create(String.class);
        var entity = Instancio.create(NotificationsEntity.class);
        var domain = Instancio.create(Notifications.class);

        when(notificationsRepository.findById(id)).thenReturn(java.util.Optional.of(entity));
        when(entityToDomainConverter.convert(entity)).thenReturn(domain);

        Notifications result = notificationsJpaGateway.findById(id);

        assertEquals(domain, result);
    }

    @Test
    @DisplayName("Should throw exception when notification not found by ID")
    void shouldThrowExceptionWhenFindByIdFails() {
        String id = Instancio.create(String.class);

        when(notificationsRepository.findById(id)).thenReturn(java.util.Optional.empty());

        assertThrows(BusinessException.class, () -> notificationsJpaGateway.findById(id));
    }

    @Test
    @DisplayName("Should save notification successfully")
    void shouldSaveSuccessfully() {
        var domain = Instancio.create(Notifications.class);
        var entity = Instancio.create(NotificationsEntity.class);
        var savedEntity = Instancio.create(NotificationsEntity.class);
        var savedDomain = Instancio.create(Notifications.class);

        when(domainToEntityConverter.convert(domain)).thenReturn(entity);
        when(notificationsRepository.save(entity)).thenReturn(savedEntity);
        when(entityToDomainConverter.convert(savedEntity)).thenReturn(savedDomain);

        Notifications result = notificationsJpaGateway.save(domain);

        assertEquals(savedDomain, result);
    }
}
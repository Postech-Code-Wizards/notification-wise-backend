package br.com.wise.notification.gateway.database.mongo;

import br.com.wise.notification.domain.NotificationLogs;
import br.com.wise.notification.gateway.database.mongo.converter.NotificationLogsDomainToEntityConverter;
import br.com.wise.notification.gateway.database.mongo.converter.NotificationLogsEntityToDomainConverter;
import br.com.wise.notification.gateway.database.mongo.entities.NotificationLogsEntity;
import br.com.wise.notification.gateway.database.mongo.repositories.NotificationLogsRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class NotificationLogsJpaGatewayTest {

    @Mock
    private NotificationLogsRepository notificationLogsRepository;

    @Mock
    private NotificationLogsDomainToEntityConverter domainToEntityConverter;

    @Mock
    private NotificationLogsEntityToDomainConverter entityToDomainConverter;

    @InjectMocks
    private NotificationLogsJpaGateway notificationLogsJpaGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should save notification logs successfully")
    void shouldSaveSuccessfully() {
        NotificationLogs domain = Instancio.create(NotificationLogs.class);
        var entity = Instancio.create(NotificationLogsEntity.class);
        var savedEntity = Instancio.create(NotificationLogsEntity.class);
        NotificationLogs savedDomain = Instancio.create(NotificationLogs.class);

        when(domainToEntityConverter.convert(domain)).thenReturn(entity);
        when(notificationLogsRepository.save(entity)).thenReturn(savedEntity);
        when(entityToDomainConverter.convert(savedEntity)).thenReturn(savedDomain);

        NotificationLogs result = notificationLogsJpaGateway.save(domain);

        assertEquals(savedDomain, result);
    }
}
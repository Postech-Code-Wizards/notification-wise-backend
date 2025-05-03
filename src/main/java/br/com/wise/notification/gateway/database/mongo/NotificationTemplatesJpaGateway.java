package br.com.wise.notification.gateway.database.mongo;

import br.com.wise.notification.domain.NotificationTemplates;
import br.com.wise.notification.gateway.database.NotificationTemplatesGateway;
import br.com.wise.notification.gateway.database.mongo.converter.NotificationTemplatesDomainToEntityConverter;
import br.com.wise.notification.gateway.database.mongo.repositories.NotificationTemplatesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationTemplatesJpaGateway implements NotificationTemplatesGateway {

    private final NotificationTemplatesRepository notificationTemplatesRepository;
    private final NotificationTemplatesDomainToEntityConverter notificationTemplatesDomainToEntityConverter;

    @Override
    public NotificationTemplates findById(Long id) {
        return null;
    }

    @Override
    public NotificationTemplates findByNameTemplate(String name) {
        return null;
    }

    @Override
    public NotificationTemplates save(NotificationTemplates notificationTemplates) {
        var notificationTemplatesEntity = notificationTemplatesDomainToEntityConverter.convert(notificationTemplates);
        notificationTemplatesRepository.save(notificationTemplatesEntity);

        // todo ajustar
        return notificationTemplates;
    }

    @Override
    public void delete(Long id) {

    }

}

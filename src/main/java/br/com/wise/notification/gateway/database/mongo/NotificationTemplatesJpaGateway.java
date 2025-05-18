package br.com.wise.notification.gateway.database.mongo;

import br.com.wise.notification.domain.NotificationTemplates;
import br.com.wise.notification.gateway.database.NotificationTemplatesGateway;
import br.com.wise.notification.gateway.database.mongo.converter.NotificationTemplatesDomainToEntityConverter;
import br.com.wise.notification.gateway.database.mongo.converter.NotificationTemplatesEntityToDomainConverter;
import br.com.wise.notification.gateway.database.mongo.repositories.NotificationTemplatesRepository;
import br.com.wise.notification.infrastructure.controller.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationTemplatesJpaGateway implements NotificationTemplatesGateway {

    private static final String NOTIFICATION_TEMPLATES_DOES_NOT_EXIST = "Notification template not found";

    private final NotificationTemplatesRepository notificationTemplatesRepository;
    private final NotificationTemplatesDomainToEntityConverter notificationTemplatesDomainToEntityConverter;
    private final NotificationTemplatesEntityToDomainConverter notificationTemplatesEntityToDomainConverter;

    @Override
    public NotificationTemplates findById(String id) {
        var notificationTemplatesEntity = notificationTemplatesRepository.findById(id)
                .orElseThrow(() -> new BusinessException(NOTIFICATION_TEMPLATES_DOES_NOT_EXIST));

        return notificationTemplatesEntityToDomainConverter.convert(notificationTemplatesEntity);
    }

    @Override
    public NotificationTemplates findByName(String name) {
        var notificationTemplatesEntity = notificationTemplatesRepository.findByName(name)
                .orElseThrow(() -> new BusinessException(NOTIFICATION_TEMPLATES_DOES_NOT_EXIST));

        return notificationTemplatesEntityToDomainConverter.convert(notificationTemplatesEntity);
    }

    @Override
    public List<NotificationTemplates> findAll() {
        var notificationTemplatesEntityList = notificationTemplatesRepository.findAll();
        return notificationTemplatesEntityList.stream().map(notificationTemplatesEntityToDomainConverter::convert).toList();
    }

    @Override
    public NotificationTemplates save(NotificationTemplates notificationTemplates) {
        var notificationTemplatesEntity = notificationTemplatesDomainToEntityConverter.convert(notificationTemplates);
        var notificationTemplatesEntitySaved = notificationTemplatesRepository.save(notificationTemplatesEntity);
        return notificationTemplatesEntityToDomainConverter.convert(notificationTemplatesEntitySaved);
    }

    @Override
    public NotificationTemplates update(NotificationTemplates notificationTemplates) {

        notificationTemplatesRepository.findById(notificationTemplates.getId())
                .orElseThrow(() -> new BusinessException(NOTIFICATION_TEMPLATES_DOES_NOT_EXIST));

        var notificationTemplatesEntity = notificationTemplatesDomainToEntityConverter.convert(notificationTemplates);
        var notificationTemplatesEntitySaved = notificationTemplatesRepository.save(notificationTemplatesEntity);
        return notificationTemplatesEntityToDomainConverter.convert(notificationTemplatesEntitySaved);
    }

    @Override
    public boolean delete(String id) {
        notificationTemplatesRepository.deleteById(id);
        return true;
    }

}

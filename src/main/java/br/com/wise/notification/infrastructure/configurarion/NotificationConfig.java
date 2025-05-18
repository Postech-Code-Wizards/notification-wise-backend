package br.com.wise.notification.infrastructure.configurarion;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {

    public static final String EMAIL_QUEUE_NAME = "notification_email_queue";
    public static final String SMS_QUEUE_NAME = "notification_sms_queue";
    public static final String WHATSAPP_QUEUE_NAME = "notification_whatsapp_queue";
    public static final String EMAIL_ROUTING_KEY = "notification_email";
    public static final String SMS_ROUTING_KEY = "notification_sms";
    public static final String WHATSAPP_ROUTING_KEY = "notification_whatsapp";

    @Bean
    public Queue emailQueue() {
        return new Queue(EMAIL_QUEUE_NAME, true);
    }

    @Bean
    public Queue smsQueue() {
        return new Queue(SMS_QUEUE_NAME, true);
    }

    @Bean
    public Queue whatsAppQueue() {
        return new Queue(WHATSAPP_QUEUE_NAME, true);
    }

    @Bean
    public Binding emailBinding(Queue emailQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(emailQueue).to(directExchange).with(EMAIL_ROUTING_KEY);
    }

    @Bean
    public Binding smsBinding(Queue smsQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(smsQueue).to(directExchange).with(SMS_ROUTING_KEY);
    }

    @Bean
    public Binding whatsAppBinding(Queue whatsAppQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(whatsAppQueue).to(directExchange).with(WHATSAPP_ROUTING_KEY);
    }

}
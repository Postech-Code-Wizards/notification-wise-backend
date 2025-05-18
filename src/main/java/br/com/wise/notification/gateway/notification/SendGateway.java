package br.com.wise.notification.gateway.notification;

import br.com.wise.notification.domain.StreamMessage;

public interface SendGateway {

    void send(StreamMessage message);

}
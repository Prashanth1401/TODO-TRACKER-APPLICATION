package com.todo.config;

import com.todo.rabbitmq.domain.ArchiveDTO;
import com.todo.rabbitmq.domain.ImpServiceDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    private RabbitTemplate rabbitTemplate;
    private DirectExchange exchange;

    @Autowired
    public Producer(RabbitTemplate rabbitTemplate, DirectExchange exchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }

    public void sendMessageToRabbitTemplate(ImpServiceDTO impServiceDTO){
        rabbitTemplate.convertAndSend(exchange.getName(),"impservice_routing",impServiceDTO);
    }

    public void sendMessageToRabbitTemplate2(ArchiveDTO archiveDTO){
        rabbitTemplate.convertAndSend(exchange.getName(),"archive_routing",archiveDTO);
    }
}

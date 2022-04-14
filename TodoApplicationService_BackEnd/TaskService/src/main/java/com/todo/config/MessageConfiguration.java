package com.todo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {

    private String exchangeName = "task_exchange";
    private String registerQueue = "impservice_queue";

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Queue registerQueue(){
        return new Queue(registerQueue,true);
    }

    @Bean
    public Queue registerQueue2(){
        return new Queue("archive_queue",true);
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2JsonConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemp = new RabbitTemplate(connectionFactory);
        rabbitTemp.setMessageConverter(producerJackson2JsonConverter());
        return rabbitTemp;
    }

    @Bean
    public Binding bindingImpService(Queue registerQueue, DirectExchange exchange){
        return BindingBuilder.bind(registerQueue()).to(exchange).with("impservice_routing");
    }

    @Bean
    public Binding bindingImpService2(Queue registerQueue, DirectExchange exchange){
        return BindingBuilder.bind(registerQueue2()).to(exchange).with("archive_routing");
    }
}

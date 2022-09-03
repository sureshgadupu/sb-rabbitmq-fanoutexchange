package dev.fullstackcode.sb.rabbitmq.producer.controller;

import dev.fullstackcode.sb.rabbitmq.producer.model.Event;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="rabbitmq/event")
public class RabbitMQProducerController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private FanoutExchange fanoutExchange;


    @PostMapping
    public String  send(@RequestBody Event event) {

         rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", event);

        return "message sent successfully";
    }

}

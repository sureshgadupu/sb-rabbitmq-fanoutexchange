package dev.fullstackcode.sb.rabbitmq.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    Queue queueA() {
        return new Queue("queue.A", false);
    }

    @Bean
    Queue queueB() {
        return new Queue("queue.B", false);
    }

    @Bean
    Queue queueC() {
        return new Queue("queue.C", false);
    }


    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange("exchange.fanout");
    }

    @Bean
    Binding bindingA(Queue queueA, FanoutExchange exchange) {
        return BindingBuilder.bind(queueA).to(exchange);
    }

    @Bean
    Binding bindingB(Queue queueB, FanoutExchange exchange) {
        return BindingBuilder.bind(queueB).to(exchange);
    }

    @Bean
    Binding bindingC(Queue queueC, FanoutExchange exchange) {
        return BindingBuilder.bind(queueC).to(exchange);
    }

    @Bean
    ApplicationRunner runner(ConnectionFactory cf) {
        return args -> cf.createConnection().close();
    }

    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}

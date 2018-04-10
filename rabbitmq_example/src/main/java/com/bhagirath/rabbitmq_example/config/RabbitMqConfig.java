package com.bhagirath.rabbitmq_example.config;

import com.bhagirath.rabbitmq_example.MessageReceiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@EnableRabbit
@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue queue() {
        return new Queue("TestQueue");
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("test_exchange");
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("routingkey");
    }

    @Bean(name = "listenerFactory")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMaxConcurrentConsumers(5);
        factory.setConcurrentConsumers(5);
        return factory;
    }

    @Scope("prototype")
    @Bean
    public MessageReceiver receiver() {
        return new MessageReceiver();
    }
}

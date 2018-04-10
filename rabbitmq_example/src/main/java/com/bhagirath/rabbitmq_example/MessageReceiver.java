package com.bhagirath.rabbitmq_example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MessageReceiver {
    @RabbitListener(queues = "TestQueue", containerFactory = "listenerFactory")
    public void receiveAndLogMessage(Message message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("Message" + objectMapper.readValue(message.getBody(), MessageBody.class) + ", Time in millis" + System.currentTimeMillis());
    }
}

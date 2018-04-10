package com.bhagirath.rabbitmq_example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    @RabbitListener
    public void receiveAndLogMessage(String message) {
        System.out.println("Message" + message + ", Time in millis" +System.currentTimeMillis());
    }
}

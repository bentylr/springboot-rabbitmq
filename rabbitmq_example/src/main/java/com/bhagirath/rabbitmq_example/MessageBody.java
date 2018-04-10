package com.bhagirath.rabbitmq_example;

import lombok.Data;

@Data
public class MessageBody {
    private String message;
    private Integer number;
}

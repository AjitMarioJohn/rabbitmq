package com.example.rabbitmq.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class MqEventMessage implements Serializable {
    private String message;
    private String time;
}

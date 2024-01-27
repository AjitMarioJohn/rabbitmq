package com.example.rabbitmq.listeners;

import com.example.rabbitmq.models.MqEventMessage;
import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Log
public class RabbitMqListener {

    @RabbitListener(queues = "mobile")
    public void getMessage(MqEventMessage mqEventMessage) {
        log.info("received message");

        log.info(String.format("message %s", mqEventMessage));
    }

}

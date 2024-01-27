package com.example.rabbitmq.controllers;

import com.example.rabbitmq.models.MqEventMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

import static com.example.rabbitmq.commons.CommonUtils.QUEUE_NAME;

@RestController
@RequiredArgsConstructor
@Log
public class MessagingQueueController {
    private final RabbitTemplate rabbitTemplate;

    @PostMapping("/send")
    public ResponseEntity<String> sendToQueue(@RequestBody Map<String, String> message) {
        Objects.requireNonNull(message);
        final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("DD/MM/YYYY HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        MqEventMessage sendMqEventMessage = new MqEventMessage();
        sendMqEventMessage.setMessage(message.get("message"));
        sendMqEventMessage.setTime(localDateTime.format(timeFormatter));

        log.info(String.format("Message %s is going to sent to rabbitmq", sendMqEventMessage.toString()));
        rabbitTemplate.convertAndSend(QUEUE_NAME, sendMqEventMessage);
        log.info("message send");
        return ResponseEntity.ok().body("Message send");
    }

}

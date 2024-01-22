package org.rabbitmq.integration.consumers;

import com.rabbitmq.client.*;

public class RabbitmqConsumerWithoutExchange {
    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String msg = new String(delivery.getBody());
                System.out.println("receiver   "+msg);
            };

            CancelCallback cancelCallback = consumerTag -> {};

            channel.basicConsume("Queue-1", true, deliverCallback, cancelCallback);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

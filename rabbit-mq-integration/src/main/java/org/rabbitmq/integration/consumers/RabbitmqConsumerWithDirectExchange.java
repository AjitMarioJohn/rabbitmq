package org.rabbitmq.integration.consumers;

import com.rabbitmq.client.*;

public class RabbitmqConsumerWithDirectExchange {
    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String msg = new String(delivery.getBody());
                String routingKey = delivery.getEnvelope().getRoutingKey();
                System.out.println(routingKey+" queue   "+msg);
            };

            CancelCallback cancelCallback = consumerTag -> {};

            channel.basicConsume("mobile", true, deliverCallback, cancelCallback);
            channel.basicConsume("TV", true, deliverCallback, cancelCallback);
            channel.basicConsume("AC", true, deliverCallback, cancelCallback);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

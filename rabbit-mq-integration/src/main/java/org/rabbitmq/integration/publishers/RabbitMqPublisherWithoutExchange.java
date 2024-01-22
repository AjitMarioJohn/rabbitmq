package org.rabbitmq.integration.publishers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMqPublisherWithoutExchange {
    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();

            int count = 1;

            while (count < 8) {
                String msg= String.format("message to rabbitmq  -- %d", count++);
                channel.basicPublish("", "Queue-1", null, msg.getBytes());
            }

            channel.close();
            connection.close();

            System.out.println("Message send");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

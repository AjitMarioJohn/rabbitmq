package org.rabbitmq.integration.publishers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Random;

public class RabbitMqPublisherWIthDirectExchange {

    public static void main(String[] args) {
        final int keylength = RoutingKey.values().length;
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection connection = connectionFactory.newConnection()) {
            Channel channel = connection.createChannel();

            int count = 1;

            while (count < 8) {
                RoutingKey key = RoutingKey.values()[new Random().nextInt(keylength)];
                String msg = String.format("This message is send to key %s  -- %d", key.getKey(), count++);
                System.out.println(msg);
                channel.basicPublish("Direct-Exchange", key.getKey(), null, msg.getBytes());
            }

            channel.close();
            System.out.println("Message send");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static enum RoutingKey {
        MOBILE("mobile"), AC("ac"), TV("tv");

        private final String key;

        RoutingKey(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }
}

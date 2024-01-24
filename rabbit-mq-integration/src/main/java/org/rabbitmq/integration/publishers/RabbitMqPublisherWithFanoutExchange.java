package org.rabbitmq.integration.publishers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang3.StringUtils;

public class RabbitMqPublisherWithFanoutExchange {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection connection = connectionFactory.newConnection()) {
            Channel channel = connection.createChannel();

            int count = 1;

            while (count < 8) {
                String msg = String.format("This message is send to via fanout with count %d",  count++);
                System.out.println(msg);
                channel.basicPublish("Fanout-Exchange", StringUtils.EMPTY, null, msg.getBytes());
            }

            channel.close();
            System.out.println("Message send");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

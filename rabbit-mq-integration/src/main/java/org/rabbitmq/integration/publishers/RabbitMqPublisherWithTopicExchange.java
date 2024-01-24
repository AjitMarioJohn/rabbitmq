package org.rabbitmq.integration.publishers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang3.StringUtils;

public class RabbitMqPublisherWithTopicExchange {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (
                Connection connection = connectionFactory.newConnection();
                Channel channel = connection.createChannel();
            ) {


            String msg = String.format("This message is send to via mobile queue");
            System.out.println(msg);
            channel.basicPublish("Topic-Exchange", "tv.mobile.ac", null, msg.getBytes());

            System.out.println("Message send");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

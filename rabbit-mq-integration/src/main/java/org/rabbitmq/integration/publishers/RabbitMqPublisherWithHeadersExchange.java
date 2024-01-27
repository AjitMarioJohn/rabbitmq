package org.rabbitmq.integration.publishers;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class RabbitMqPublisherWithHeadersExchange {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (
                Connection connection = connectionFactory.newConnection();
                Channel channel = connection.createChannel();
            ) {

            Map<String, Object> headers = Map.of(
                    "item1", "mobile",
                    "item2", "television"
            );

            BasicProperties basicProperties = new AMQP.BasicProperties()
                    .builder()
                    .headers(headers)
                    .build();

            String msg = String.format("This message for mobile and tv queue");
            System.out.println(msg);
            channel.basicPublish("Headers-Exchange", StringUtils.EMPTY, basicProperties, msg.getBytes());

            System.out.println("Message send");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

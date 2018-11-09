package com.carpooling.simulator;

import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

public class Simulator {

    @Autowired
    private Channel channel;

    public Simulator() {

    }

    @PostConstruct
    public void simulate() {
        while(true) {
            try {
                channel.queueDeclare("paths.simulator", false, false, false, null);
                Consumer consumer = new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope,
                                               AMQP.BasicProperties properties, byte[] body)
                            throws IOException {
                        String message = new String(body, "UTF-8");
                        System.out.println(" Message received '" + message + "'");
                    }
                };
                channel.basicConsume("paths.simulator", true, consumer);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

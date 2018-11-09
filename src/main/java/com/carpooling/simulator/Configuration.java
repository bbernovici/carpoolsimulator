package com.carpooling.simulator;


import com.rabbitmq.client.Channel;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public Channel rabbitMqChannel() {
        return new RabbitMQConnector().getChannel();
    }

    @Bean
    public Simulator simulator() {
        return new Simulator();
    }
}

package com.southsystem.votos.rabbit.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.amqp.rabbit.connection.CachingConnectionFactory.ConfirmType.CORRELATED;

@EnableRabbit
@Configuration
public class RabbitConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private Integer port;

    @Value("${spring.rabbitmq.username}")
    private String user;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtualhost}")
    private String virtualHost;

    @Bean
    public ConnectionFactory rabbitConnectionFactory() {
        final CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(user);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setPublisherConfirmType(CORRELATED);
        connectionFactory.createConnection();
        return connectionFactory;
    }

    @Bean
    public MessageConverter jsonMessageConverter(final ObjectMapper objectMapper) {

        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory jsaFactory(final ConnectionFactory connectionFactory,
                                                           final SimpleRabbitListenerContainerFactoryConfigurer listenerContainerFactoryConf,
                                                           final ObjectMapper objectMapper) {
        final SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();

        listenerContainerFactoryConf.configure(factory, connectionFactory);
        factory.setMessageConverter(jsonMessageConverter(objectMapper));
        return factory;
    }
}


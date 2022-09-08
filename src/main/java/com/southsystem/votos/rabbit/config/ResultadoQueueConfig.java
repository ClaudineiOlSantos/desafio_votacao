package com.southsystem.votos.rabbit.config;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.southsystem.votos.rabbit.config.ParametrosRabbit.*;


@Configuration
@AllArgsConstructor
public class ResultadoQueueConfig {

    private final ExchangeConfig exchangeConfig;
    private final QueueConfig queueConfig;

    @Bean
    Queue resultadoQueue() {
        return QueueBuilder.durable(queueConfig.getResultado().getVotacao().getNome())
                .withArgument(X_DEAD_LETTER_EXCHANGE, exchangeConfig.getResultado())
                .withArgument(X_DEAD_LETTER_ROUTING_KEY, queueConfig.getResultado().getVotacao().getDlq().getRoute())
                .build();
    }

    @Bean
    Queue resultadoErrorQueue() {
        return QueueBuilder.durable(queueConfig.getResultado().getVotacao().getDlq().getQueue())
                .withArgument(X_DEAD_LETTER_EXCHANGE, exchangeConfig.getResultado())
                .withArgument(X_DEAD_LETTER_ROUTING_KEY, queueConfig.getResultado().getVotacao().getRoute())
                .withArgument(X_MESSAGE_TTL, queueConfig.getResultado().getVotacao().getDlq().getTtl())
                .build();
    }

    @Bean
    Queue resultadoParkinglotQueue() {
        return QueueBuilder.durable(queueConfig.getResultado().getVotacao().getPlq().getQueue()).build();
    }

    @Bean
    Binding contasQueueToExchangeBinder() {
        return BindingBuilder.bind(resultadoQueue())
                .to(resultadoExchange())
                .with(queueConfig.getResultado().getVotacao().getRoute());
    }

    @Bean
    Binding contasErrorQueueToExchangeBinder() {
        return BindingBuilder.bind(resultadoErrorQueue())
                .to(resultadoExchange())
                .with(queueConfig.getResultado().getVotacao().getDlq().getRoute());
    }

    @Bean
    Binding contasQueueParkinglotQueueToExchangeBinder() {
        return BindingBuilder.bind(resultadoParkinglotQueue())
                .to(resultadoExchange())
                .with(queueConfig.getResultado().getVotacao().getPlq().getRoute());
    }

    @Bean
    public TopicExchange resultadoExchange(){
        return new TopicExchange(exchangeConfig.getResultado());
    }
}

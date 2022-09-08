package com.southsystem.votos.rabbit.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "rabbit.exchange")
public class ExchangeConfig {

    private String resultado;
}

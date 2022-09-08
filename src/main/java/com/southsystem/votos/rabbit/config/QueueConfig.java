package com.southsystem.votos.rabbit.config;

import com.southsystem.votos.rabbit.config.dto.ResultadoQueue;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "rabbit.queue")
public class QueueConfig {

    private ResultadoQueue resultado;

}

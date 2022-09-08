package com.southsystem.votos.rabbit.config.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class ResultadoQueue {

    @Value("${votacao}")
    private Queue votacao;
}

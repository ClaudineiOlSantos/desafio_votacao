package com.southsystem.votos.rabbit.config.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Queue {

    private String route;

    private String nome;

    private DeadLetterQueue dlq;

    private ParkinglotQueue plq;
}

package com.southsystem.votos.rabbit.config.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeadLetterQueue {

    private long ttl;

    private String route;

    private String queue;
}

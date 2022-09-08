package com.southsystem.votos.producer;

import com.southsystem.votos.dto.ResultadoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ResultadoProducer {

    @Value("${rabbit.exchange.resultado}")
    private String exchange;

    @Value("${rabbit.queue.resultado.votacao.route}")
    private String routeKey;

    @Autowired
    public RabbitTemplate rabbitTemplate;

    public void enviar(ResultadoResponse message) {
        log.info("Enviando resultado votação da pauta para fila :{}", message.getPautaId());

        rabbitTemplate.convertAndSend(exchange, routeKey, message);
    }

}

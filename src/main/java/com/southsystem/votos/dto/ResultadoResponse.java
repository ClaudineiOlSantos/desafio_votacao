package com.southsystem.votos.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.southsystem.votos.enumeration.VotoEnum;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.util.UUID;

@Value
@With
@JsonDeserialize(builder = ResultadoResponse.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class ResultadoResponse {

    UUID pautaId;

    String pautaTitulo;

    long sim;

    long nao;

    BigDecimal simPercentual;

    BigDecimal naoPercentual;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}

package com.southsystem.votos.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@With
@JsonDeserialize(builder = SessaoResponse.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class SessaoResponse {

    UUID id;

    UUID pautaId;

    String pautaTitulo;

    String pautaDescricao;

    LocalDateTime inicio;

    LocalDateTime fim;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}

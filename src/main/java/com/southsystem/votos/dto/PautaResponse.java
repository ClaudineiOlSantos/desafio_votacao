package com.southsystem.votos.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.UUID;

@Value
@With
@JsonDeserialize(builder = PautaResponse.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class PautaResponse {

    UUID id;

    String titulo;

    String descricao;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}

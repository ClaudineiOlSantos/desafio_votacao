package com.southsystem.votos.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import javax.validation.constraints.NotEmpty;

@Value
@With
@JsonDeserialize(builder = PautaRequest.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class PautaRequest {

    @NotEmpty
    String titulo;

    @NotEmpty
    String descricao;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}

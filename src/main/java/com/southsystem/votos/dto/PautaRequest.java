package com.southsystem.votos.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import javax.validation.constraints.NotEmpty;

import static com.southsystem.votos.exception.messages.ErrorsMessages.OBRIGATORIO;

@Value
@With
@JsonDeserialize(builder = PautaRequest.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class PautaRequest {

    @NotEmpty(message = "Titulo" + OBRIGATORIO)
    String titulo;

    @NotEmpty(message = "Descricao" + OBRIGATORIO)
    String descricao;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}

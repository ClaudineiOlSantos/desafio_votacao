package com.southsystem.votos.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static com.southsystem.votos.exception.messages.ErrorsMessages.OBRIGATORIO;

@Value
@With
@JsonDeserialize(builder = AssociadoRequest.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class AssociadoRequest {

    @NotEmpty(message = "cpf" + OBRIGATORIO)
    @Size(min = 11, max = 11, message = "CPF deve conter 11 dígitos")
    String cpf;

    @NotEmpty(message = "nome" + OBRIGATORIO)
    String nome;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }

}

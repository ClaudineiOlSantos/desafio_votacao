package com.southsystem.votos.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.southsystem.votos.enumeration.VotoEnum;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

import static com.southsystem.votos.exception.messages.ErrorsMessages.OBRIGATORIO;

@Value
@With
@JsonDeserialize(builder = VotoRequest.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class VotoRequest {

    @NotEmpty(message = "CPF" + OBRIGATORIO)
    @Size(min = 11, max = 11, message = "CPF deve conter 11 dígitos")
    String cpf;

    @NotNull(message = "Voto" + OBRIGATORIO)
    VotoEnum voto;

    @NotNull(message = "SessaoId" + OBRIGATORIO)
    UUID sessaoId;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}

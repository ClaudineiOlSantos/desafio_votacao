package com.southsystem.votos.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.southsystem.votos.enumeration.VotoEnum;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@With
@JsonDeserialize(builder = VotoRequest.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class VotoRequest {

    @NotEmpty(message = "CPF é Obrigatório")
    String cpf;

    @NotNull(message = "Voto é Obrigatório")
    VotoEnum voto;

    @NotNull(message = "SessaoId é Obrigatório")
    UUID sessaoId;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}

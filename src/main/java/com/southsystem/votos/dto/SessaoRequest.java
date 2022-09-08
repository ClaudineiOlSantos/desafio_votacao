package com.southsystem.votos.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.southsystem.votos.exception.messages.ErrorsMessages.OBRIGATORIO;

@Value
@With
@JsonDeserialize(builder = SessaoRequest.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class SessaoRequest {

    @NotNull(message = "PautaId" + OBRIGATORIO)
    UUID pautaId;

    LocalDateTime inicio;

    LocalDateTime fim;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}

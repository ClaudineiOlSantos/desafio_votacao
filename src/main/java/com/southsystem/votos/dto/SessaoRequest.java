package com.southsystem.votos.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
@With
@JsonDeserialize(builder = SessaoRequest.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class SessaoRequest {

    @NotNull(message = "PautaId é obrigatória")
    UUID pautaId;

    LocalDateTime inicio;

    LocalDateTime fim;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}

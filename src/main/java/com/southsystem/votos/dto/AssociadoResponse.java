package com.southsystem.votos.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.UUID;

@Value
@With
@JsonDeserialize(builder = AssociadoResponse.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class AssociadoResponse {

    UUID id;

    String cpf;

    String nome;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }

}

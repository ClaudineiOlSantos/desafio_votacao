package com.southsystem.votos.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Value
@With
@JsonDeserialize(builder = AssociadoRequest.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class AssociadoRequest {

    @NotEmpty(message = "cpf é obrigatório")
    @Size(min=11, max=11,message = "CPF inválido")
    String cpf;

    @NotEmpty(message = "nome é obrigatório")
    String nome;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }

}

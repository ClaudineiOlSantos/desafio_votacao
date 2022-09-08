package com.southsystem.votos.integration;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@JsonDeserialize(builder = EnableVoteResponse.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class EnableVoteResponse {

    String status;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {

    }
}

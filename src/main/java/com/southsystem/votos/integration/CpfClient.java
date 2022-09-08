package com.southsystem.votos.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CpfClient", url = "https://user-info.herokuapp.com/users")
public interface CpfClient {

    @GetMapping(value = "/{cpf}", consumes = MediaType.APPLICATION_JSON_VALUE)
    EnableVoteResponse consultarCpf(@PathVariable("cpf") String cpf);
}

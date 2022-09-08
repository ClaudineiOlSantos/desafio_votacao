package com.southsystem.votos.builder;

import com.southsystem.votos.dto.AssociadoResponse;
import com.southsystem.votos.entity.AssociadoEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AssociadoResponseBuilder {

    public static AssociadoResponse buildAssociadoResponse(AssociadoEntity entity){
        return AssociadoResponse.builder()
                .id(entity.getId())
                .cpf(entity.getCpf())
                .nome(entity.getNome()).build();
    }
}

package com.southsystem.votos.builder;

import com.southsystem.votos.dto.PautaResponse;
import com.southsystem.votos.entity.PautaEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PautaBuilder {

    public static PautaResponse buildPautaResponse(PautaEntity entity){
        return PautaResponse.builder()
                .id(entity.getId())
                .titulo(entity.getTitulo())
                .descricao(entity.getDescricao())
                .build();
    }
}

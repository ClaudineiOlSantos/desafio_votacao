package com.southsystem.votos.builder;

import com.southsystem.votos.dto.SessaoResponse;
import com.southsystem.votos.entity.SessaoEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SessaoBuilder {

    public static SessaoResponse buildSessaoResponse(SessaoEntity sessao) {
        return SessaoResponse
                .builder()
                .id(sessao.getId())
                .inicio(sessao.getInicio())
                .fim(sessao.getFim())
                .pautaId(sessao.getPauta().getId())
                .pautaTitulo(sessao.getPauta().getTitulo())
                .pautaDescricao(sessao.getPauta().getDescricao())
                .build();
    }
}

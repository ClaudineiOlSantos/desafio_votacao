package com.southsystem.votos.service;

import com.southsystem.votos.builder.SessaoBuilder;
import com.southsystem.votos.dto.SessaoRequest;
import com.southsystem.votos.dto.SessaoResponse;
import com.southsystem.votos.entity.SessaoEntity;
import com.southsystem.votos.exception.NotFoundException;
import com.southsystem.votos.repository.PautaRepository;
import com.southsystem.votos.repository.SessaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.southsystem.votos.builder.SessaoBuilder.buildSessaoResponse;
import static com.southsystem.votos.exception.messages.ErrorsMessages.PAUTA_NAO_ENCONTRADA;
import static com.southsystem.votos.exception.messages.ErrorsMessages.SESSAO_NAO_ENCONTRADA;
import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class SessaoService {

    final SessaoRepository sessaoRepository;
    final PautaRepository pautaRepository;

    public List<SessaoResponse> listar() {
        return sessaoRepository.findAll().stream().map(SessaoBuilder::buildSessaoResponse).collect(Collectors.toList());
    }

    public SessaoResponse salvar(SessaoRequest request) {

        var pauta = pautaRepository.findById(request.getPautaId()).orElseThrow(() -> new NotFoundException(PAUTA_NAO_ENCONTRADA));

        return buildSessaoResponse(sessaoRepository.save(SessaoEntity.builder()
                .pauta(pauta)
                .inicio(ofNullable(request.getInicio()).orElse(LocalDateTime.now()))
                .fim(ofNullable(request.getFim()).orElse(LocalDateTime.now().plusMinutes(1)))
                .build()));
    }

    public SessaoResponse buscarPorId(UUID id) {
        return sessaoRepository.findById(id).map(SessaoBuilder::buildSessaoResponse).orElseThrow(() -> new NotFoundException(SESSAO_NAO_ENCONTRADA));
    }
}

package com.southsystem.votos.service;

import com.southsystem.votos.builder.PautaBuilder;
import com.southsystem.votos.dto.PautaRequest;
import com.southsystem.votos.dto.PautaResponse;
import com.southsystem.votos.entity.PautaEntity;
import com.southsystem.votos.exception.NotFoundException;
import com.southsystem.votos.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.southsystem.votos.builder.PautaBuilder.buildPautaResponse;
import static com.southsystem.votos.exception.messages.ErrorsMessages.PAUTA_NAO_ENCONTRADA;

@Service
@RequiredArgsConstructor
public class PautaService {

    final PautaRepository pautaRepository;

    public List<PautaResponse> listar() {
       return pautaRepository.findAll().stream().map(PautaBuilder::buildPautaResponse).collect(Collectors.toList());
    }

    public PautaResponse buscarPorId(UUID id) {
        return pautaRepository.findById(id).map(PautaBuilder::buildPautaResponse)
                .orElseThrow(()-> new NotFoundException(PAUTA_NAO_ENCONTRADA));
    }

    public PautaResponse salvar(PautaRequest request) {
        return buildPautaResponse(pautaRepository.save(
                PautaEntity.builder()
                        .titulo(request.getTitulo())
                        .descricao(request.getDescricao())
                        .build()));
    }


}

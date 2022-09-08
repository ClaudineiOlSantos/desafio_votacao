package com.southsystem.votos.service;

import com.southsystem.votos.builder.AssociadoResponseBuilder;
import com.southsystem.votos.dto.AssociadoRequest;
import com.southsystem.votos.dto.AssociadoResponse;
import com.southsystem.votos.entity.AssociadoEntity;
import com.southsystem.votos.exception.NotFoundException;
import com.southsystem.votos.repository.AssociadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.southsystem.votos.builder.AssociadoResponseBuilder.buildAssociadoResponse;
import static com.southsystem.votos.exception.messages.ErrorsMessages.ASSOCIADO_NAO_ENCONTRADO;

@Service
@RequiredArgsConstructor
public class AssociadoService {

    final AssociadoRepository associadoRepository;

    public List<AssociadoResponse> listar() {
        return associadoRepository.findAll().stream().map(AssociadoResponseBuilder::buildAssociadoResponse).collect(Collectors.toList());
    }

    public AssociadoResponse buscarPorId(UUID id) {
        return associadoRepository.findById(id)
                .map(AssociadoResponseBuilder::buildAssociadoResponse)
                .orElseThrow(() -> new NotFoundException(ASSOCIADO_NAO_ENCONTRADO));
    }

    public AssociadoResponse salvar(AssociadoRequest request) {

        return buildAssociadoResponse(associadoRepository.save(AssociadoEntity.builder()
                .cpf(request.getCpf())
                .nome(request.getNome()).build()));
    }
}

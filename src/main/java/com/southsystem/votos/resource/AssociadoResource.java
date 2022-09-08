package com.southsystem.votos.resource;

import com.southsystem.votos.dto.AssociadoRequest;
import com.southsystem.votos.dto.AssociadoResponse;
import com.southsystem.votos.service.AssociadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/associado")
@RequiredArgsConstructor
public class AssociadoResource {

    final AssociadoService associadoService;

    @GetMapping
    public List<AssociadoResponse> listar() {
        return associadoService.listar();
    }

    @GetMapping("/{id}")
    public AssociadoResponse buscarPorId(@PathVariable("id") UUID id) {
        return associadoService.buscarPorId(id);
    }

    @PostMapping
    public AssociadoResponse salvar(@RequestBody @Valid AssociadoRequest request) {
        return associadoService.salvar(request);
    }
}

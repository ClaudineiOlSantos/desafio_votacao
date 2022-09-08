package com.southsystem.votos.resource;

import com.southsystem.votos.dto.PautaRequest;
import com.southsystem.votos.dto.PautaResponse;
import com.southsystem.votos.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pauta")
@RequiredArgsConstructor
public class PautaResource {

    final PautaService pautaService;

    @GetMapping
    public List<PautaResponse> listar() {
        return pautaService.listar();
    }

    @GetMapping("/{id}")
    public PautaResponse buscarPorId(@PathVariable("id") UUID id) {
        return pautaService.buscarPorId(id);
    }

    @PostMapping
    public PautaResponse salvar(@RequestBody @Valid PautaRequest request) {
        return pautaService.salvar(request);
    }
}

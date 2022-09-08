package com.southsystem.votos.resource;

import com.southsystem.votos.dto.SessaoRequest;
import com.southsystem.votos.dto.SessaoResponse;
import com.southsystem.votos.service.SessaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/sessao")
@RequiredArgsConstructor
public class SessaoResource {

    final SessaoService sessaoService;

    @GetMapping
    public List<SessaoResponse> listar() {
        return sessaoService.listar();
    }

    @GetMapping("/{id}")
    public SessaoResponse buscarPorId(@PathVariable("id") UUID id) {
        return sessaoService.buscarPorId(id);
    }

    @PostMapping
    public SessaoResponse salvar(@RequestBody @Valid SessaoRequest request) {
        return sessaoService.salvar(request);
    }
}

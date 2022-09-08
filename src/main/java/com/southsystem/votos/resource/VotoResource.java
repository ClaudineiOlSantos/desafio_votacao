package com.southsystem.votos.resource;

import com.southsystem.votos.dto.ResultadoResponse;
import com.southsystem.votos.dto.VotoRequest;
import com.southsystem.votos.service.VotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/voto")
@RequiredArgsConstructor
public class VotoResource {

    final VotoService votoService;

    @PostMapping
    public void votar(@RequestBody @Valid VotoRequest request) {
        votoService.votar(request);
    }

    @GetMapping("/resultado/{sessaoId}")
    public ResultadoResponse resultado(@PathVariable UUID sessaoId) {
        return votoService.resultado(sessaoId);
    }
}

package com.southsystem.votos.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.southsystem.votos.dto.PautaRequest;
import com.southsystem.votos.entity.PautaEntity;
import com.southsystem.votos.fixture.Fixture;
import com.southsystem.votos.repository.PautaRepository;
import com.southsystem.votos.service.PautaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.southsystem.votos.builder.PautaBuilder.buildPautaResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PautaResource.class)
class PautaResourceTest {
    private static final String URL = "/api/pauta";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PautaResource pautaResource;

    @Autowired
    PautaService pautaService;

    @MockBean
    PautaRepository pautaRepository;

    private PautaEntity pauta;
    private List<PautaEntity> pautas = new ArrayList<>();

    @BeforeEach
    public void setup() {
        pauta = Fixture.make(PautaEntity.builder().build());
        pautas.add(pauta);

        when(pautaRepository.findById(any())).thenReturn(Optional.of(pauta));
        when(pautaRepository.findAll()).thenReturn(pautas);
        when(pautaRepository.save(any())).thenReturn(pauta);
    }

    @Test
    void listarPautas_ok() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get(URL)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().isOk()).andReturn();
    }

    @Test
    void buscarPauta_PorId_ok() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get(URL + "/{id}", pauta.getId())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(buildPautaResponse(pauta))));
    }

    @Test
    void persistirPauta_ok() throws Exception {
        var pautaRequest = PautaRequest.builder()
                .titulo(pauta.getTitulo()).descricao(pauta.getDescricao()).build();
        mockMvc.perform(
                        post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(pautaRequest)))

                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(buildPautaResponse(pauta))));
    }


    @TestConfiguration
    public static class PautaServiceConfig {

        @Bean
        PautaService pautaServiceBean(PautaRepository pautaRepository) {
            return new PautaService(pautaRepository);
        }
    }
}

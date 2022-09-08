package com.southsystem.votos.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.southsystem.votos.dto.SessaoRequest;
import com.southsystem.votos.entity.PautaEntity;
import com.southsystem.votos.entity.SessaoEntity;
import com.southsystem.votos.fixture.Fixture;
import com.southsystem.votos.repository.PautaRepository;
import com.southsystem.votos.repository.SessaoRepository;
import com.southsystem.votos.service.SessaoService;
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

import static com.southsystem.votos.builder.SessaoBuilder.buildSessaoResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SessaoResource.class)
class SessaoResourceTest {

    private static final String URL = "/api/sessao";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    SessaoResource sessaoResource;

    @Autowired
    SessaoService sessaoService;

    @MockBean
    SessaoRepository sessaoRepository;

    @MockBean
    PautaRepository pautaRepository;


    private SessaoEntity sessao;
    private PautaEntity pauta;
    private List<SessaoEntity> sessoes = new ArrayList<>();

    @BeforeEach
    public void setup() {
        sessao = Fixture.make(SessaoEntity.builder().build());
        pauta = sessao.getPauta();
        sessoes.add(sessao);

        when(sessaoRepository.findById(any())).thenReturn(Optional.of(sessao));
        when(sessaoRepository.findAll()).thenReturn(sessoes);
        when(sessaoRepository.save(any())).thenReturn(sessao);
        when(pautaRepository.findById(any())).thenReturn(Optional.of(pauta));
    }

    @Test
    void listarSessaos_ok() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get(URL)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().isOk()).andReturn();
    }

    @Test
    void buscarSessao_PorId_ok() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get(URL + "/{id}", sessao.getId())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(buildSessaoResponse(sessao))));
    }

    @Test
    void persistirSessao_ok() throws Exception {
        var sessaoRequest = SessaoRequest.builder()
                .fim(sessao.getFim()).inicio(sessao.getInicio()).pautaId(pauta.getId()).build();
        mockMvc.perform(
                        post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(sessaoRequest)))

                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(buildSessaoResponse(sessao))));
    }


    @TestConfiguration
    public static class SessaoServiceConfig {

        @Bean
        SessaoService sessaoServiceBean(SessaoRepository sessaoRepository, PautaRepository pautaRepository) {
            return new SessaoService(sessaoRepository, pautaRepository);
        }
    }
}

package com.southsystem.votos.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.southsystem.votos.dto.ResultadoResponse;
import com.southsystem.votos.dto.VotoRequest;
import com.southsystem.votos.entity.AssociadoEntity;
import com.southsystem.votos.entity.SessaoEntity;
import com.southsystem.votos.entity.VotoEntity;
import com.southsystem.votos.enumeration.VotoEnum;
import com.southsystem.votos.exception.NotFoundException;
import com.southsystem.votos.fixture.Fixture;
import com.southsystem.votos.integration.CpfClient;
import com.southsystem.votos.integration.EnableVoteResponse;
import com.southsystem.votos.producer.ResultadoProducer;
import com.southsystem.votos.repository.AssociadoRepository;
import com.southsystem.votos.repository.SessaoRepository;
import com.southsystem.votos.repository.VotoRepository;
import com.southsystem.votos.service.VotoService;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static com.southsystem.votos.enumeration.VotoEnum.NAO;
import static com.southsystem.votos.enumeration.VotoEnum.SIM;
import static com.southsystem.votos.exception.messages.ErrorsMessages.SESSAO_NAO_ENCONTRADA;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VotoResource.class)
public class VotoResourceTest {
    private static final String URL = "/api/voto";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    VotoResource votoResource;

    @Autowired
    VotoService votoService;

    @MockBean
    VotoRepository votoRepository;

    @MockBean
    AssociadoRepository associadoRepository;

    @MockBean
    SessaoRepository sessaoRepository;

    @MockBean
    ResultadoProducer resultadoProducer;

    @MockBean
    CpfClient client;

    VotoEntity voto;
    SessaoEntity sessao;
    AssociadoEntity associado;
    Set<VotoEntity> votos = new HashSet<>();

    @BeforeEach
    public void setup() {

        voto = Fixture.make(VotoEntity.builder().build());
        sessao = Fixture.make(SessaoEntity.builder().build());
        associado = Fixture.make(AssociadoEntity.builder().build());

        sessao.setInicio(LocalDateTime.now().minusMinutes(60));
        sessao.setFim(LocalDateTime.now().plusMinutes(60));

        when(client.consultarCpf(any())).thenReturn(EnableVoteResponse.builder().status("ABLE_TO_VOTE").build());
        when(associadoRepository.findByCpf(any())).thenReturn(Optional.of(associado));
        when(sessaoRepository.findById(any())).thenReturn(Optional.of(sessao));

        when(votoRepository.existsByAssociadoAndSessao(associado, sessao)).thenReturn(false);

        when(votoRepository.findById(any())).thenReturn(Optional.of(voto));
        when(votoRepository.save(any())).thenReturn(voto);

        votos.add(VotoEntity.builder().voto(SIM).associado(associado).sessao(sessao).build());
        votos.add(VotoEntity.builder().voto(NAO).associado(Fixture.make(AssociadoEntity.builder().build())).sessao(sessao).build());
        votos.add(VotoEntity.builder().voto(SIM).associado(Fixture.make(AssociadoEntity.builder().build())).sessao(sessao).build());

        when(votoRepository.findBySessao(sessao)).thenReturn(votos);
    }


    @Test
    void votar_ok() throws Exception {
        var votoRequest = VotoRequest.builder().voto(VotoEnum.SIM).cpf("12345678912").sessaoId(UUID.randomUUID()).build();
        mockMvc.perform(
                        post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(votoRequest)))

                .andExpect(status().isOk());

    }

    @Test
    void votar_not_able_ok() throws Exception {
        var votoRequest = VotoRequest.builder().voto(VotoEnum.SIM).cpf("12345678912").sessaoId(UUID.randomUUID()).build();

        when(client.consultarCpf(any())).thenReturn(EnableVoteResponse.builder().status("UNABLE_TO_VOTE").build());

        mockMvc.perform(
                        post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(votoRequest)))

                .andExpect(status().isNotAcceptable());

    }

    @Test
    void resultado_ok() throws Exception {


        RequestBuilder request = MockMvcRequestBuilders.get(URL + "/resultado/"+sessao.getId(), voto.getId())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(
                        ResultadoResponse.builder()
                                .pautaTitulo(sessao.getPauta().getTitulo())
                                .pautaId(sessao.getPauta().getId())
                                .nao(1)
                                .sim(2)
                                .naoPercentual(BigDecimal.valueOf(33.33))
                                .simPercentual(BigDecimal.valueOf(66.67))
                                .build())));
    }


    @TestConfiguration
    public static class VotoServiceConfig {

        @Bean
        VotoService votoServiceBean(
                VotoRepository votoRepository,
                AssociadoRepository associadoRepository,
                SessaoRepository sessaoRepository,
                ResultadoProducer resultadoProducer,
                CpfClient client) {
            return new VotoService(
                    votoRepository,
                    associadoRepository,
                    sessaoRepository,
                    resultadoProducer,
                    client);
        }
    }
}


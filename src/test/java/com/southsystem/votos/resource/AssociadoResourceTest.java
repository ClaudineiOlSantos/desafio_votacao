package com.southsystem.votos.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.southsystem.votos.dto.AssociadoRequest;
import com.southsystem.votos.entity.AssociadoEntity;
import com.southsystem.votos.repository.AssociadoRepository;
import com.southsystem.votos.service.AssociadoService;
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
import java.util.UUID;

import static com.southsystem.votos.builder.AssociadoResponseBuilder.buildAssociadoResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AssociadoResource.class)
class AssociadoResourceTest {

    private static final String URL = "/api/associado";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AssociadoResource associadoResource;

    @Autowired
    AssociadoService associadoService;

    @MockBean
    AssociadoRepository associadoRepository;

    private AssociadoEntity associado;
    private List<AssociadoEntity> associados = new ArrayList<>();

    @BeforeEach
    public void setup() {
        associado = AssociadoEntity.builder().id(UUID.randomUUID()).cpf("12345678912").nome("Fulano").build();
        associados.add(associado);

        when(associadoRepository.findById(any())).thenReturn(Optional.of(associado));
        when(associadoRepository.findAll()).thenReturn(associados);
        when(associadoRepository.save(any())).thenReturn(associado);
    }

    @Test
    void listarAssociados_ok() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get(URL)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().isOk()).andReturn();
    }

    @Test
    void buscarAssociado_PorId_ok() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get(URL + "/{id}", associado.getId())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(buildAssociadoResponse(associado))));
    }

    @Test
    void persistirAssociado_ok() throws Exception {
        var associadoRequest = AssociadoRequest.builder()
                .cpf(associado.getCpf()).nome(associado.getNome()).build();
        mockMvc.perform(
                        post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(associadoRequest)))

                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(buildAssociadoResponse(associado))));
    }


    @TestConfiguration
    public static class AssociadoServiceConfig {

        @Bean
        AssociadoService associadoServiceBean(AssociadoRepository associadoRepository) {
            return new AssociadoService(associadoRepository);
        }
    }
}

package com.southsystem.votos.service;

import com.southsystem.votos.dto.ResultadoResponse;
import com.southsystem.votos.dto.VotoRequest;
import com.southsystem.votos.entity.AssociadoEntity;
import com.southsystem.votos.entity.SessaoEntity;
import com.southsystem.votos.entity.VotoEntity;
import com.southsystem.votos.exception.NotFoundException;
import com.southsystem.votos.exception.ValidationException;
import com.southsystem.votos.producer.ResultadoProducer;
import com.southsystem.votos.repository.AssociadoRepository;
import com.southsystem.votos.repository.SessaoRepository;
import com.southsystem.votos.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static com.southsystem.votos.enumeration.VotoEnum.NAO;
import static com.southsystem.votos.enumeration.VotoEnum.SIM;
import static com.southsystem.votos.exception.messages.ErrorsMessages.*;
import static java.math.RoundingMode.HALF_EVEN;

@Slf4j
@Service
@RequiredArgsConstructor
public class VotoService {

    final VotoRepository votoRepository;
    final AssociadoRepository associadoRepository;
    final SessaoRepository sessaoRepository;
    final ResultadoProducer resultadoProducer;

    public void votar(VotoRequest request) {
        log.info("Registrando voto sessão:{}", request.getSessaoId());

        var associado = associadoRepository.findByCpf(request.getCpf())
                .orElseThrow(() -> new NotFoundException(ASSOCIADO_NAO_ENCONTRADO));

        var sessao = sessaoRepository.findById(request.getSessaoId())
                .orElseThrow(() -> new NotFoundException(ASSOCIADO_NAO_ENCONTRADO));

        verificarSeJaVotou(associado, sessao);

        validarData(sessao);

        votoRepository.save(VotoEntity.builder()
                .associado(associado)
                .sessao(sessao)
                .voto(request.getVoto())
                .build());
    }

    private void verificarSeJaVotou(AssociadoEntity associado, SessaoEntity sessao) {
        if (votoRepository.existsByAssociadoAndSessao(associado, sessao)) {
            log.error("Associado {} já efetuou voto para sessão: {}. Operação cancelada.", associado.getId(), sessao.getId());
            throw new ValidationException(ASSOCIADO_JA_VOTOU);
        }
    }

    private void validarData(SessaoEntity sessao) {
        var data = LocalDateTime.now();

        if (data.isAfter(sessao.getFim())) {
            log.error("Horário de votação da sessão: {} já encerrado operação cancelada.", sessao.getId());
            throw new ValidationException(TEMPO_EXCEDIDO);
        }

        if (data.isBefore(sessao.getInicio())) {
            log.error("Horário de votação da sessão: {} ainda não iniciado.", sessao.getId());
            throw new ValidationException(SESAO_NAO_INICIADA);
        }
    }


    public ResultadoResponse resultado(final UUID sessaoId) {

        var sessao = sessaoRepository.findById(sessaoId)
                .orElseThrow(() -> new NotFoundException(SESSAO_NAO_ENCONTRADA));

        Set<VotoEntity> votos = votoRepository.findBySessao(sessao);
        var sim = votos.stream().filter(voto -> SIM.equals(voto.getVoto())).count();
        var nao = votos.stream().filter(voto -> NAO.equals(voto.getVoto())).count();

        log.info("Apuração: SIM = {}; NÃO = {}", sim, nao);

        var resultado = ResultadoResponse.builder()
                .pautaId(sessao.getPauta().getId())
                .pautaTitulo(sessao.getPauta().getTitulo())
                .sim(sim)
                .nao(nao)
                .simPercentual(calcularPercentual(sim, votos.size()))
                .naoPercentual(calcularPercentual(nao, votos.size()))
                .build();

        resultadoProducer.enviar(resultado);

        return resultado;
    }

    private BigDecimal calcularPercentual(double votos, double totalVotos) {
        return Optional.of(votos)
                .map(v -> BigDecimal.valueOf((votos / totalVotos) * 100).setScale(2, HALF_EVEN))
                .orElse(BigDecimal.valueOf(0L).setScale(2, HALF_EVEN));
    }
}

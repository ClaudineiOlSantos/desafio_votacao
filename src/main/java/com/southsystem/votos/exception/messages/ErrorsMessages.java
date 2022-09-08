package com.southsystem.votos.exception.messages;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorsMessages {

    public static final String PAUTA_NAO_ENCONTRADA = "Pauta não encontrada.";
    public static final String ASSOCIADO_NAO_ENCONTRADO = "Associado não encontrado.";
    public static final String SESSAO_NAO_ENCONTRADA = "Sessão não encontrada.";
    public static final String TEMPO_EXCEDIDO = "Tempo limite de votação excedido";
    public static final String SESAO_NAO_INICIADA = "Sessão ainda não iniciada";
    public static final String ASSOCIADO_JA_VOTOU = "Só é permitido um voto por sessão";

}

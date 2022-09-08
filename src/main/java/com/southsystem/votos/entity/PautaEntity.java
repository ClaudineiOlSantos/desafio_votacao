package com.southsystem.votos.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "pauta")
public class PautaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    UUID id;

    @Column(name = "titulo", length = 50, nullable = false)
    String titulo;

    @Column(name = "descricao", nullable = false)
    String descricao;
}

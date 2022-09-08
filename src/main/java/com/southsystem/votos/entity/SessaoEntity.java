package com.southsystem.votos.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "sessao")
public class SessaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    UUID id;

    @OneToOne
    PautaEntity pauta;

    @Column(name = "inicio", nullable = false)
    LocalDateTime inicio;

    @Column(name = "fim", nullable = false)
    LocalDateTime fim;

}

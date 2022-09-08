package com.southsystem.votos.entity;


import com.southsystem.votos.enumeration.VotoEnum;
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
@Table(name = "voto")
public class VotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    UUID id;

    @ManyToOne
    AssociadoEntity associado;

    @ManyToOne
    SessaoEntity sessao;

    @Enumerated(EnumType.STRING)
    VotoEnum voto;

}

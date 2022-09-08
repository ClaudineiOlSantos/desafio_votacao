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
@Table(name = "associado")
public class AssociadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    UUID id;

    @Column(name = "cpf", unique = true, length = 11, nullable = false)
    String cpf;

    @Column(name = "nome", length = 50, nullable = false)
    String nome;

}

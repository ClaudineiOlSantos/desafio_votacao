package com.southsystem.votos.repository;

import com.southsystem.votos.entity.AssociadoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AssociadoRepository extends CrudRepository<AssociadoEntity, UUID> {

    List<AssociadoEntity> findAll();

    Optional<AssociadoEntity> findByCpf(final String cpf);

}

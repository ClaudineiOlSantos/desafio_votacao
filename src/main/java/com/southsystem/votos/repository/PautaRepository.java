package com.southsystem.votos.repository;

import com.southsystem.votos.entity.PautaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface PautaRepository extends CrudRepository<PautaEntity, UUID> {

    List<PautaEntity> findAll();
}

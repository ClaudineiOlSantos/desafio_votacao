package com.southsystem.votos.repository;

import com.southsystem.votos.entity.PautaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PautaRepository extends CrudRepository<PautaEntity, UUID> {

    List<PautaEntity> findAll();
}

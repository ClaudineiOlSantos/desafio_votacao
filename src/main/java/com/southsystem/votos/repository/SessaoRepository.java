package com.southsystem.votos.repository;

import com.southsystem.votos.entity.SessaoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface SessaoRepository extends CrudRepository<SessaoEntity, UUID> {


    List<SessaoEntity> findAll();
}

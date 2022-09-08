package com.southsystem.votos.repository;

import com.southsystem.votos.entity.SessaoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SessaoRepository extends CrudRepository<SessaoEntity, UUID> {


    List<SessaoEntity> findAll();
}

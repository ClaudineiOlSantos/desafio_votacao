package com.southsystem.votos.repository;

import com.southsystem.votos.entity.AssociadoEntity;
import com.southsystem.votos.entity.SessaoEntity;
import com.southsystem.votos.entity.VotoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;
import java.util.UUID;

public interface VotoRepository extends CrudRepository<VotoEntity, UUID> {

    boolean existsByAssociadoAndSessao(AssociadoEntity associado, SessaoEntity sessao);

    Set<VotoEntity> findBySessao(SessaoEntity sessao);
}

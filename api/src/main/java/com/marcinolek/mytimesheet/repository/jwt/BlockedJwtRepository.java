package com.marcinolek.mytimesheet.repository.jwt;

import com.marcinolek.mytimesheet.entity.jwt.BlockedJwtEntity;
import com.marcinolek.mytimesheet.repository.base.AbstractRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BlockedJwtRepository extends AbstractRepository <BlockedJwtEntity, Long> {

    Optional<BlockedJwtEntity> findByToken(String token);

    List<BlockedJwtEntity> findAllByEndBlockDateBefore(LocalDate date);

}

package com.marcinolek.mytimesheet.repository.base;

import com.marcinolek.mytimesheet.entity.base.BaseExtendedEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseExtendedRepository<TEntity extends BaseExtendedEntity, ID> extends BaseRepository<TEntity, ID> {
    @Override
    @Query("SELECT e FROM #{#entityName} e WHERE e.removed IS NULL OR e.removed = false")
    List<TEntity> findAll();

    @Override
    @Query("SELECT e FROM #{#entityName} e WHERE e.removed IS NULL OR e.removed = false")
    Page<TEntity> findAll(Pageable pageable);

    @Override
    @Query("SELECT e FROM #{#entityName} e WHERE e.id = :id AND (e.removed IS NULL OR e.removed = false)")
    Optional<TEntity> findById(ID id);
}
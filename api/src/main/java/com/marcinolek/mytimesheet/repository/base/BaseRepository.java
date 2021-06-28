package com.marcinolek.mytimesheet.repository.base;

import com.marcinolek.mytimesheet.entity.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<TEntity extends BaseEntity, Long> extends JpaRepository<TEntity, Long> {
}
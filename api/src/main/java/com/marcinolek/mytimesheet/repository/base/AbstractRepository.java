package com.marcinolek.mytimesheet.repository.base;

import com.marcinolek.mytimesheet.entity.base.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface AbstractRepository<TEntity extends AbstractEntity, ID extends Serializable>
        extends JpaRepository<TEntity, ID>, JpaSpecificationExecutor<TEntity> {
}
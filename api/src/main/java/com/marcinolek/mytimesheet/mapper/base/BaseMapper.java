package com.marcinolek.mytimesheet.mapper.base;

import com.marcinolek.mytimesheet.dto.base.AbstractDTO;
import com.marcinolek.mytimesheet.entity.base.AbstractEntity;

import java.util.List;

public interface BaseMapper <TDto extends AbstractDTO, TEntity extends AbstractEntity> {
    TEntity toEntity(TDto dto);

    TDto toDto(TEntity entity);

    List<TEntity> toEntityList(List<TDto> dtoList);

    List<TDto> toDtoList(List<TEntity> entityList);
}

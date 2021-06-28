package com.marcinolek.mytimesheet.mapper.base;

import com.marcinolek.mytimesheet.dto.base.BaseDTO;
import com.marcinolek.mytimesheet.entity.base.BaseEntity;

import java.util.List;

public interface BaseMapper <TDto extends BaseDTO, TEntity extends BaseEntity> {
    TEntity toEntity(TDto dto);

    TDto toDto(TEntity entity);

    List<TEntity> toEntityList(List<TDto> dtoList);

    List<TDto> toDtoList(List<TEntity> entityList);
}

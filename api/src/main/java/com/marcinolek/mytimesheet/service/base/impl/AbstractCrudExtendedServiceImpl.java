package com.marcinolek.mytimesheet.service.base.impl;

import com.marcinolek.mytimesheet.constants.exception.WebApiExceptionType;
import com.marcinolek.mytimesheet.dto.base.AbstractExtendedDTO;
import com.marcinolek.mytimesheet.entity.base.AbstractExtendedEntity;
import com.marcinolek.mytimesheet.exception.WebApiException;
import com.marcinolek.mytimesheet.repository.base.AbstractExtendedRepository;
import com.marcinolek.mytimesheet.service.base.AbstractCrudExtendedService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class AbstractCrudExtendedServiceImpl<TEntity extends AbstractExtendedEntity, TDto extends AbstractExtendedDTO, ID extends Serializable>
        extends AbstractCrudServiceImpl<TEntity, TDto, ID> implements AbstractCrudExtendedService<TEntity, TDto, ID> {

    @Autowired
    private AbstractExtendedRepository<TEntity, ID> extendedRepository;

    @Override
    public TDto update(TDto dto, ID id) throws WebApiException {
        TEntity entity = this.extendedRepository.findById(id)
                .orElseThrow(() -> new WebApiException(WebApiExceptionType.ENTITY_NOT_FOUND));
        TEntity newValuesEntity = this.mapper.toEntity(dto);
        BeanUtils.copyProperties(newValuesEntity, entity, DEFAULT_IGNORE_PROPERTIES);
        entity.setEditDate(LocalDateTime.now());
        try {
            return mapper.toDto(this.extendedRepository.save(entity));
        } catch (Exception e) {
            throw new WebApiException(WebApiExceptionType.SAVE_FAILED);
        }
    }

    @Override
    public TDto create(TDto dto) throws WebApiException {
        TEntity entity = this.mapper.toEntity(dto);
        entity.setRemoved(false);
        entity.setCreateDate(LocalDateTime.now());
        try {
            return this.mapper.toDto(this.extendedRepository.save(entity));
        } catch (Exception e) {
            throw new WebApiException(WebApiExceptionType.SAVE_FAILED);
        }
    }

    @Override
    public void deleteById(ID id) throws WebApiException {
        TEntity entity = this.extendedRepository.findById(id)
                .orElseThrow(() -> new WebApiException(WebApiExceptionType.ENTITY_NOT_FOUND));
        entity.setRemoved(true);
        try {
            this.extendedRepository.save(entity);
        } catch (Exception e) {
            throw new WebApiException(WebApiExceptionType.DELETE_FAILED);
        }
    }

}

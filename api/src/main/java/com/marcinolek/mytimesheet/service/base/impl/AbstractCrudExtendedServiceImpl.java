package com.marcinolek.mytimesheet.service.base.impl;

import com.marcinolek.mytimesheet.constants.exception.WebApiExceptionType;
import com.marcinolek.mytimesheet.dto.base.AbstractExtendedDTO;
import com.marcinolek.mytimesheet.entity.base.AbstractExtendedEntity;
import com.marcinolek.mytimesheet.exception.WebApiException;
import com.marcinolek.mytimesheet.repository.base.AbstractExtendedRepository;
import com.marcinolek.mytimesheet.service.base.AbstractCrudExtendedService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public abstract class AbstractCrudExtendedServiceImpl<TEntity extends AbstractExtendedEntity, TDto extends AbstractExtendedDTO, ID extends Serializable>
        extends AbstractCrudServiceImpl<TEntity, TDto, ID> implements AbstractCrudExtendedService<TEntity, TDto, ID> {

    @Autowired
    private AbstractExtendedRepository<TEntity, ID> extendedRepository;

    @Override
    public TDto update(TDto dto, ID id) throws WebApiException {
        TEntity entity = this.extendedRepository.findById(id)
                .orElseThrow(() -> {
                    this.logErrorMessage(String.format("Entity with id %s not found", id));
                    return new WebApiException(WebApiExceptionType.ENTITY_NOT_FOUND);
                });
        TEntity newValuesEntity = this.mapper.toEntity(dto);
        BeanUtils.copyProperties(newValuesEntity, entity, this.getIgnoreProperties());
        entity.setEditDate(LocalDateTime.now());
        entity.setEditedBy(this.loggedUserProvider.getUsername());
        try {
            return mapper.toDto(this.extendedRepository.save(entity));
        } catch (Exception e) {
            this.logErrorMessage(e.getMessage());
            throw new WebApiException(WebApiExceptionType.SAVE_FAILED);
        }
    }

    @Override
    public TDto create(TDto dto) throws WebApiException {
        TEntity entity = this.mapper.toEntity(dto);
        entity.setRemoved(false);
        entity.setCreateDate(LocalDateTime.now());
        entity.setCreatedBy(this.loggedUserProvider.getUsername());
        try {
            return this.mapper.toDto(this.extendedRepository.save(entity));
        } catch (Exception e) {
            e.printStackTrace();
            this.logErrorMessage(e.getMessage());
            throw new WebApiException(WebApiExceptionType.SAVE_FAILED);
        }
    }

    @Override
    public void deleteById(ID id) throws WebApiException {
        try {
            TEntity entity = this.extendedRepository.findById(id)
                    .orElseThrow();
            entity.setRemoved(true);
            this.extendedRepository.save(entity);
        } catch (Exception e) {
            this.logErrorMessage(e.getMessage());
            throw new WebApiException(WebApiExceptionType.DELETE_FAILED);
        }
    }

    @Override
    public void deleteAll(List<TDto> dtoList) throws WebApiException {
        for (TDto dto : dtoList) {
            this.deleteById((ID) dto.getId());
        }
    }

    @Override
    protected String[] getIgnoreProperties() {
        return ArrayUtils.addAll(super.getIgnoreProperties(), "createDate", "editDate", "createdBy", "editedBy", "removed");
    }
}

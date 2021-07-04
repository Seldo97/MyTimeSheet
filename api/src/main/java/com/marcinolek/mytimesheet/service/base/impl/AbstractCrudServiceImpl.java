package com.marcinolek.mytimesheet.service.base.impl;

import com.marcinolek.mytimesheet.constants.exception.WebApiExceptionType;
import com.marcinolek.mytimesheet.dto.base.AbstractDTO;
import com.marcinolek.mytimesheet.dto.pagination.PaginationRequestDTO;
import com.marcinolek.mytimesheet.entity.base.AbstractEntity;
import com.marcinolek.mytimesheet.exception.WebApiException;
import com.marcinolek.mytimesheet.mapper.base.BaseMapper;
import com.marcinolek.mytimesheet.repository.base.AbstractRepository;
import com.marcinolek.mytimesheet.service.base.AbstractCrudService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractCrudServiceImpl<TEntity extends AbstractEntity, TDto extends AbstractDTO, ID extends Serializable> implements AbstractCrudService<TEntity, TDto, ID> {

    @Autowired
    protected AbstractRepository<TEntity, ID> repository;

    @Autowired
    protected BaseMapper<TDto, TEntity> mapper;

    @Override
    public List<TDto> findAll() {
        return this.mapper.toDtoList(this.repository.findAll());
    }

    @Override
    public Page<TDto> findAll(PaginationRequestDTO paginationRequest) {
        Sort sort = Sort.by(paginationRequest.getSortDirection(), paginationRequest.getOrderPropertyName());
        PageRequest page = PageRequest.of(paginationRequest.getPage(), paginationRequest.getItemsPerPage(), sort);
        return this.repository.findAll(page).map(this.mapper::toDto);
    }

    @Override
    public TDto findById(ID id) throws WebApiException {
        return this.mapper.toDto(this.repository.findById(id)
                .orElseThrow(() -> new WebApiException(WebApiExceptionType.ENTITY_NOT_FOUND)));
    }

    @Override
    public TDto update(TDto dto, ID id) throws WebApiException {
        TEntity entity = this.repository.findById(id).orElseThrow(() -> new WebApiException(WebApiExceptionType.ENTITY_NOT_FOUND));
        TEntity newValuesEntity = this.mapper.toEntity(dto);
        BeanUtils.copyProperties(newValuesEntity, entity, DEFAULT_IGNORE_PROPERTIES);
        try {
            return mapper.toDto(this.repository.save(entity));
        } catch (Exception e) {
            throw new WebApiException(WebApiExceptionType.SAVE_FAILED);
        }
    }

    @Override
    public TDto create(TDto dto) throws WebApiException {
        TEntity entity = this.mapper.toEntity(dto);
        try {
            return this.mapper.toDto(this.repository.save(entity));
        } catch (Exception e) {
            throw new WebApiException(WebApiExceptionType.SAVE_FAILED);
        }
    }

    @Override
    public void deleteById(ID id) throws WebApiException {
        try {
            this.repository.deleteById(id);
        } catch (Exception e) {
            throw new WebApiException(WebApiExceptionType.DELETE_FAILED);
        }
    }
}

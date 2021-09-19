package com.marcinolek.mytimesheet.service.base;

import com.marcinolek.mytimesheet.dto.base.AbstractDTO;
import com.marcinolek.mytimesheet.dto.pagination.PaginationRequestDTO;
import com.marcinolek.mytimesheet.entity.base.AbstractEntity;
import com.marcinolek.mytimesheet.exception.WebApiException;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

public interface AbstractCrudService<TEntity extends AbstractEntity, TDto extends AbstractDTO, ID extends Serializable> {

    List<TDto> findAll();

    Page<TDto> findAll(PaginationRequestDTO paginationRequest);

    TDto findById(ID id) throws WebApiException;

    TDto update(TDto dto, ID id) throws WebApiException;

    TDto create(TDto dto) throws WebApiException;

    void deleteById(ID id) throws WebApiException;

    void deleteAll(List<TDto> dtoList) throws WebApiException;
}

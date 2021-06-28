package com.marcinolek.mytimesheet.service.base;

import com.marcinolek.mytimesheet.dto.base.BaseDTO;
import com.marcinolek.mytimesheet.dto.pagination.PaginationRequestDTO;
import com.marcinolek.mytimesheet.entity.base.BaseEntity;
import com.marcinolek.mytimesheet.exception.WebApiException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CRUDService<TEntity extends BaseEntity, TDto extends BaseDTO> {

    List<TEntity> findAll();

    Page<TEntity> findAll(PaginationRequestDTO paginationRequest);

    TEntity findById(Long id) throws WebApiException;

    TEntity save(TEntity entity);

    void deleteById(Long id);
}

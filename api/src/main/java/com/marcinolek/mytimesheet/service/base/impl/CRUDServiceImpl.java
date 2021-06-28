package com.marcinolek.mytimesheet.service.base.impl;

import com.marcinolek.mytimesheet.constants.exception.WebApiExceptionType;
import com.marcinolek.mytimesheet.dto.base.BaseDTO;
import com.marcinolek.mytimesheet.dto.pagination.PaginationRequestDTO;
import com.marcinolek.mytimesheet.entity.base.BaseEntity;
import com.marcinolek.mytimesheet.exception.WebApiException;
import com.marcinolek.mytimesheet.repository.base.BaseRepository;
import com.marcinolek.mytimesheet.service.base.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CRUDServiceImpl<TEntity extends BaseEntity, TDto extends BaseDTO> implements CRUDService<TEntity, TDto> {

    @Autowired
    private BaseRepository<TEntity, Long> repository;

    @Override
    public List<TEntity> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Page<TEntity> findAll(PaginationRequestDTO paginationRequest) {
        Sort sort = Sort.by(paginationRequest.getSortDirection(), paginationRequest.getOrderPropertyName());
        PageRequest page = PageRequest.of(paginationRequest.getPage(), paginationRequest.getItemsPerPage(), sort);
        return this.repository.findAll(page);
    }

    @Override
    public TEntity findById(Long id) throws WebApiException {
        return this.repository.findById(id).orElseThrow(() -> new WebApiException(WebApiExceptionType.ENTITY_NOT_FOUND));
    }

    @Override
    public TEntity save(TEntity entity) {
        return this.repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
}

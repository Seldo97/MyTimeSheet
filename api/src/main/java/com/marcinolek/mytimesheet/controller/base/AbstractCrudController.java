package com.marcinolek.mytimesheet.controller.base;

import com.marcinolek.mytimesheet.dto.base.AbstractDTO;
import com.marcinolek.mytimesheet.dto.pagination.PaginationRequestDTO;
import com.marcinolek.mytimesheet.dto.pagination.PaginationResponseDTO;
import com.marcinolek.mytimesheet.entity.base.AbstractEntity;
import com.marcinolek.mytimesheet.exception.WebApiException;
import com.marcinolek.mytimesheet.mapper.base.BaseMapper;
import com.marcinolek.mytimesheet.service.base.AbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractCrudController<TEntity extends AbstractEntity, TDto extends AbstractDTO, ID extends Serializable>
        extends BaseController {

    @Autowired
    private AbstractCrudService<TEntity, TDto, ID> service;

    @Autowired
    private BaseMapper<TDto, TEntity> mapper;

    @GetMapping("")
    public List<TDto> getAll() {
        return this.service.findAll();
    }

    @PostMapping("/query")
    public PaginationResponseDTO<TDto> getAll(@RequestBody PaginationRequestDTO request) {
        Page<TDto> result = this.service.findAll(request);
        return new PaginationResponseDTO<>(result);
    }

    @GetMapping("/{id}")
    public TDto getById(@PathVariable ID id) throws WebApiException {
        return this.service.findById(id);
    }

    @PutMapping("/{id}")
    public TDto update(@PathVariable ID id, @RequestBody TDto dto) throws WebApiException {
        return this.service.update(dto, id);
    }

    @PostMapping("")
    public TDto create(@RequestBody TDto dto) throws WebApiException {
        return this.service.create(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteById(@PathVariable ID id) throws WebApiException {
        this.service.deleteById(id);
    }


}

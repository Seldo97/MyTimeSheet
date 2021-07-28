package com.marcinolek.mytimesheet.controller.base;

import com.marcinolek.mytimesheet.controller.auth.AuthController;
import com.marcinolek.mytimesheet.dto.base.AbstractDTO;
import com.marcinolek.mytimesheet.dto.pagination.PaginationRequestDTO;
import com.marcinolek.mytimesheet.dto.pagination.PaginationResponseDTO;
import com.marcinolek.mytimesheet.entity.base.AbstractEntity;
import com.marcinolek.mytimesheet.exception.WebApiException;
import com.marcinolek.mytimesheet.mapper.base.AbstractMapper;
import com.marcinolek.mytimesheet.service.base.AbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractCrudController<TEntity extends AbstractEntity, TDto extends AbstractDTO, ID extends Serializable>
        extends AuthController {

    @Autowired
    private AbstractCrudService<TEntity, TDto, ID> service;

    @Autowired
    private AbstractMapper<TDto, TEntity> mapper;

    @GetMapping("")
    public List<TDto> getAll() {
        return this.service.findAll();
    }

    @PostMapping("/query")
    public PaginationResponseDTO<TDto> getAll(@RequestBody PaginationRequestDTO request) throws WebApiException {
        this.checkReadPermission();
        Page<TDto> result = this.service.findAll(request);
        return new PaginationResponseDTO<>(result);
    }

    @GetMapping("/{id}")
    public TDto getById(@PathVariable ID id) throws WebApiException {
        this.checkReadPermission();
        return this.service.findById(id);
    }

    @PutMapping("/{id}")
    public TDto update(@PathVariable ID id, @RequestBody TDto dto) throws WebApiException {
        this.checkUpdatePermission();
        return this.service.update(dto, id);
    }

    @PostMapping("")
    public TDto create(@RequestBody TDto dto) throws WebApiException {
        this.checkCreatePermission();
        return this.service.create(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteById(@PathVariable ID id) throws WebApiException {
        this.checkDeletePermission();
        this.service.deleteById(id);
    }

    protected abstract void checkCreatePermission() throws WebApiException;

    protected abstract void checkReadPermission() throws WebApiException;

    protected abstract void checkUpdatePermission() throws WebApiException;

    protected abstract void checkDeletePermission() throws WebApiException;

}

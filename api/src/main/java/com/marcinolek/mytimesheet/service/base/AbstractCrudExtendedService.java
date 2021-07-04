package com.marcinolek.mytimesheet.service.base;

import com.marcinolek.mytimesheet.dto.base.AbstractExtendedDTO;
import com.marcinolek.mytimesheet.entity.base.AbstractExtendedEntity;

import java.io.Serializable;

public interface AbstractCrudExtendedService<TEntity extends AbstractExtendedEntity, TDto extends AbstractExtendedDTO, ID extends Serializable>
        extends AbstractCrudService<TEntity , TDto, ID> {
}

package com.marcinolek.mytimesheet.service.tag;

import com.marcinolek.mytimesheet.dto.tag.TagDTO;
import com.marcinolek.mytimesheet.entity.tag.TagEntity;
import com.marcinolek.mytimesheet.service.base.AbstractCrudService;

public interface TagService extends AbstractCrudService<TagEntity, TagDTO, Long> {
}

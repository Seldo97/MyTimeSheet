package com.marcinolek.mytimesheet.service.tag.impl;

import com.marcinolek.mytimesheet.dto.tag.TagDTO;
import com.marcinolek.mytimesheet.entity.tag.TagEntity;
import com.marcinolek.mytimesheet.service.base.impl.AbstractCrudServiceImpl;
import com.marcinolek.mytimesheet.service.tag.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends AbstractCrudServiceImpl<TagEntity, TagDTO, Long> implements TagService {
}

package com.marcinolek.mytimesheet.mapper.tag;

import com.marcinolek.mytimesheet.dto.tag.TagDTO;
import com.marcinolek.mytimesheet.entity.tag.TagEntity;
import com.marcinolek.mytimesheet.mapper.base.AbstractMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper extends AbstractMapper<TagDTO, TagEntity> {
}

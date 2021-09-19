package com.marcinolek.mytimesheet.mapper.jwt;

import com.marcinolek.mytimesheet.dto.jwt.BlockedJwtDTO;
import com.marcinolek.mytimesheet.entity.jwt.BlockedJwtEntity;
import com.marcinolek.mytimesheet.mapper.base.AbstractMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BlockedJwtMapper extends AbstractMapper<BlockedJwtDTO, BlockedJwtEntity> {
}

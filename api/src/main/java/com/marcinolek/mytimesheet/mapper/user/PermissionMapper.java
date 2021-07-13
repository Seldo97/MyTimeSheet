package com.marcinolek.mytimesheet.mapper.user;

import com.marcinolek.mytimesheet.dto.user.PermissionDTO;
import com.marcinolek.mytimesheet.entity.user.PermissionEntity;
import com.marcinolek.mytimesheet.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface PermissionMapper extends BaseMapper<PermissionDTO, PermissionEntity> {

    Set<PermissionEntity> toEntitySet(Set<PermissionDTO> dtoList);

    Set<PermissionDTO> toDtoSet(Set<PermissionEntity> entityList);
}

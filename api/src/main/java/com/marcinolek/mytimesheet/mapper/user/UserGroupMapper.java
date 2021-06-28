package com.marcinolek.mytimesheet.mapper.user;

import com.marcinolek.mytimesheet.dto.user.UserGroupDTO;
import com.marcinolek.mytimesheet.entity.user.UserGroupEntity;
import com.marcinolek.mytimesheet.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserGroupMapper extends BaseMapper<UserGroupDTO, UserGroupEntity> {
}

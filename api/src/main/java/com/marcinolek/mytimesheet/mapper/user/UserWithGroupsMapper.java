package com.marcinolek.mytimesheet.mapper.user;

import com.marcinolek.mytimesheet.dto.user.UserDTO;
import com.marcinolek.mytimesheet.dto.user.UserWithGroupsDTO;
import com.marcinolek.mytimesheet.entity.user.UserEntity;
import com.marcinolek.mytimesheet.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserGroupMapper.class})
public interface UserWithGroupsMapper extends BaseMapper<UserWithGroupsDTO, UserEntity> {
}

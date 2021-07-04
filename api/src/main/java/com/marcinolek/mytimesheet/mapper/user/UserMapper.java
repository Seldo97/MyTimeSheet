package com.marcinolek.mytimesheet.mapper.user;

import com.marcinolek.mytimesheet.dto.user.UserDTO;
import com.marcinolek.mytimesheet.entity.user.UserEntity;
import com.marcinolek.mytimesheet.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper <UserDTO, UserEntity> {
}
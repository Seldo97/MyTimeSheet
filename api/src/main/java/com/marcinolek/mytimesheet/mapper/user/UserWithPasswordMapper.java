package com.marcinolek.mytimesheet.mapper.user;

import com.marcinolek.mytimesheet.dto.user.UserWithPasswordDTO;
import com.marcinolek.mytimesheet.entity.user.UserEntity;
import com.marcinolek.mytimesheet.mapper.base.AbstractMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserWithPasswordMapper extends AbstractMapper<UserWithPasswordDTO, UserEntity> {
}

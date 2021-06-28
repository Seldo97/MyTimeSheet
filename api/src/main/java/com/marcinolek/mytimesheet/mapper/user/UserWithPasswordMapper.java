package com.marcinolek.mytimesheet.mapper.user;

import com.marcinolek.mytimesheet.dto.user.UserWithPasswordDTO;
import com.marcinolek.mytimesheet.entity.user.UserEntity;
import com.marcinolek.mytimesheet.mapper.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserGroupMapper.class})
public interface UserWithPasswordMapper extends BaseMapper<UserWithPasswordDTO, UserEntity>  {
}

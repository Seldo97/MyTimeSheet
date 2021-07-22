package com.marcinolek.mytimesheet.service.user;

import com.marcinolek.mytimesheet.dto.user.UserDTO;
import com.marcinolek.mytimesheet.dto.user.UserWithRolesDTO;
import com.marcinolek.mytimesheet.entity.user.UserEntity;
import com.marcinolek.mytimesheet.exception.WebApiException;
import com.marcinolek.mytimesheet.service.base.AbstractCrudExtendedService;

public interface UserService extends AbstractCrudExtendedService<UserEntity, UserDTO, Long> {

    UserWithRolesDTO getUserWithRolesById(Long id) throws WebApiException;

    UserDTO getUserByUsername(String username) throws WebApiException;

}

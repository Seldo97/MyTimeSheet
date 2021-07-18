package com.marcinolek.mytimesheet.service.user;

import com.marcinolek.mytimesheet.dto.user.UserDTO;
import com.marcinolek.mytimesheet.entity.user.UserEntity;
import com.marcinolek.mytimesheet.exception.WebApiException;
import com.marcinolek.mytimesheet.service.base.AbstractCrudExtendedService;

import java.util.List;
import java.util.Set;

public interface UserService extends AbstractCrudExtendedService<UserEntity, UserDTO, Long> {

    UserDTO findUserByUsername(String username) throws WebApiException;

}

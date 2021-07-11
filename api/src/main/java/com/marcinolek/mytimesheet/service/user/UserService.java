package com.marcinolek.mytimesheet.service.user;

import com.marcinolek.mytimesheet.dto.user.UserDTO;
import com.marcinolek.mytimesheet.dto.user.UserWithGroupsDTO;
import com.marcinolek.mytimesheet.entity.user.UserEntity;
import com.marcinolek.mytimesheet.exception.WebApiException;
import com.marcinolek.mytimesheet.service.base.AbstractCrudExtendedService;

import java.util.List;

public interface UserService extends AbstractCrudExtendedService<UserEntity, UserDTO, Long> {

    UserWithGroupsDTO findByIdWithGroups(Long id) throws WebApiException;

    List<UserWithGroupsDTO> findAllWithGroups() throws WebApiException;

    UserDTO findUserByUsername(String username) throws WebApiException;

}

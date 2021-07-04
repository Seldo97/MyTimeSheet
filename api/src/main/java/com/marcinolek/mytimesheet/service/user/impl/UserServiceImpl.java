package com.marcinolek.mytimesheet.service.user.impl;

import com.marcinolek.mytimesheet.constants.exception.WebApiExceptionType;
import com.marcinolek.mytimesheet.dto.user.UserDTO;
import com.marcinolek.mytimesheet.dto.user.UserWithGroupsDTO;
import com.marcinolek.mytimesheet.entity.user.UserEntity;
import com.marcinolek.mytimesheet.exception.WebApiException;
import com.marcinolek.mytimesheet.mapper.user.UserWithGroupsMapper;
import com.marcinolek.mytimesheet.repository.user.UserRepository;
import com.marcinolek.mytimesheet.service.base.impl.AbstractCrudExtendedServiceImpl;
import com.marcinolek.mytimesheet.service.base.impl.AbstractCrudServiceImpl;
import com.marcinolek.mytimesheet.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends AbstractCrudExtendedServiceImpl<UserEntity, UserDTO, Long> implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserWithGroupsMapper userWithGroupsMapper;

    @Override
    public UserWithGroupsDTO findByIdWithGroups(Long id) throws WebApiException {
        return this.userWithGroupsMapper.toDto(this.userRepository.findByIdWithGroups(id)
                .orElseThrow(() -> new WebApiException(WebApiExceptionType.ENTITY_NOT_FOUND)));
    }

    @Override
    public List<UserWithGroupsDTO> findAllWithGroups() throws WebApiException {
        return this.userWithGroupsMapper.toDtoList(this.userRepository.findAllWithGroups());
    }

}

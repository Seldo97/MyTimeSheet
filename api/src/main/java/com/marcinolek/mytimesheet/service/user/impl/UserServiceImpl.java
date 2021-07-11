package com.marcinolek.mytimesheet.service.user.impl;

import com.marcinolek.mytimesheet.constants.exception.WebApiExceptionType;
import com.marcinolek.mytimesheet.dto.user.UserDTO;
import com.marcinolek.mytimesheet.dto.user.UserWithGroupsDTO;
import com.marcinolek.mytimesheet.dto.user.UserWithPasswordDTO;
import com.marcinolek.mytimesheet.entity.user.UserEntity;
import com.marcinolek.mytimesheet.exception.WebApiException;
import com.marcinolek.mytimesheet.mapper.user.UserMapper;
import com.marcinolek.mytimesheet.mapper.user.UserWithGroupsMapper;
import com.marcinolek.mytimesheet.mapper.user.UserWithPasswordMapper;
import com.marcinolek.mytimesheet.repository.user.UserRepository;
import com.marcinolek.mytimesheet.service.base.impl.AbstractCrudExtendedServiceImpl;
import com.marcinolek.mytimesheet.service.base.impl.AbstractCrudServiceImpl;
import com.marcinolek.mytimesheet.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl extends AbstractCrudExtendedServiceImpl<UserEntity, UserDTO, Long> implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserWithGroupsMapper userWithGroupsMapper;

    @Autowired
    UserWithPasswordMapper userWithPasswordMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public UserWithGroupsDTO findByIdWithGroups(Long id) throws WebApiException {
        return this.userWithGroupsMapper.toDto(this.userRepository.findByIdWithGroups(id)
                .orElseThrow(() -> new WebApiException(WebApiExceptionType.ENTITY_NOT_FOUND)));
    }

    @Override
    public List<UserWithGroupsDTO> findAllWithGroups() throws WebApiException {
        return this.userWithGroupsMapper.toDtoList(this.userRepository.findAllWithGroups());
    }

    @Override
    public UserDTO findUserByUsername(String username) throws WebApiException {
        return this.userMapper.toDto(userRepository.findByUsernameIgnoreCase(username).
                orElseThrow(() -> new UsernameNotFoundException(WebApiExceptionType.USER_NOT_FOUND.toString())));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserWithPasswordDTO userDTO = this.userWithPasswordMapper.toDto(userRepository.findByUsernameIgnoreCase(username).
                orElseThrow(() -> new UsernameNotFoundException(WebApiExceptionType.USER_NOT_FOUND.toString())));
        return new org.springframework.security.core.userdetails.User(userDTO.getUsername(), userDTO.getPassword(), Collections.emptyList());
    }
}

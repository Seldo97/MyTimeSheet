package com.marcinolek.mytimesheet.service.user.impl;

import com.marcinolek.mytimesheet.constants.exception.WebApiExceptionType;
import com.marcinolek.mytimesheet.dto.user.UserDTO;
import com.marcinolek.mytimesheet.dto.user.UserWithPasswordDTO;
import com.marcinolek.mytimesheet.entity.user.UserEntity;
import com.marcinolek.mytimesheet.exception.WebApiException;
import com.marcinolek.mytimesheet.mapper.user.UserMapper;
import com.marcinolek.mytimesheet.mapper.user.UserWithPasswordMapper;
import com.marcinolek.mytimesheet.repository.user.UserRepository;
import com.marcinolek.mytimesheet.service.base.impl.AbstractCrudExtendedServiceImpl;
import com.marcinolek.mytimesheet.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl extends AbstractCrudExtendedServiceImpl<UserEntity, UserDTO, Long> implements UserService, UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserWithPasswordMapper userWithPasswordMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsernameIgnoreCase(username);
        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            UserWithPasswordDTO userDTO = this.userWithPasswordMapper.toDto(userEntity);

            return new org.springframework.security.core.userdetails.User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getRoles());
        } else {
            logErrorMessage(String.format("User with username %s not found", username));
            throw new UsernameNotFoundException(WebApiExceptionType.USER_NOT_FOUND.toString());
        }
    }

    @Override
    public UserDTO findUserByUsername(String username) throws WebApiException {
        return this.userMapper.toDto(this.userRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> {
                    logErrorMessage(String.format("User with username %s not found", username));
                    return new WebApiException(WebApiExceptionType.USER_NOT_FOUND);
                }));
    }
}

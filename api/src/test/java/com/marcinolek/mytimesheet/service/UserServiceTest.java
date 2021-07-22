package com.marcinolek.mytimesheet.service;

import com.google.common.collect.Lists;
import com.marcinolek.mytimesheet.constants.permission.RoleType;
import com.marcinolek.mytimesheet.dto.user.UserDTO;
import com.marcinolek.mytimesheet.dto.user.UserWithRolesDTO;
import com.marcinolek.mytimesheet.entity.user.RoleEntity;
import com.marcinolek.mytimesheet.entity.user.UserEntity;
import com.marcinolek.mytimesheet.exception.WebApiException;
import com.marcinolek.mytimesheet.mapper.base.AbstractMapper;
import com.marcinolek.mytimesheet.mapper.user.RoleMapper;
import com.marcinolek.mytimesheet.mapper.user.UserMapper;
import com.marcinolek.mytimesheet.mapper.user.UserWithPasswordMapper;
import com.marcinolek.mytimesheet.mapper.user.UserWithRolesMapper;
import com.marcinolek.mytimesheet.repository.base.AbstractExtendedRepository;
import com.marcinolek.mytimesheet.repository.user.UserRepository;
import com.marcinolek.mytimesheet.service.base.AbstractCrudService;
import com.marcinolek.mytimesheet.service.user.UserService;
import com.marcinolek.mytimesheet.service.user.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest()
public class UserServiceTest {
    @Autowired
    RoleMapper roleMapper;

    @Mock
    UserRepository userRepository;

    @Mock
    UserWithPasswordMapper userWithPasswordMapper;

    @Mock
    UserWithRolesMapper userWithRolesMapper;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    private final UserService service = new UserServiceImpl();

    @Test
    public void getUserWithRolesByIdTest() throws WebApiException {
        // given
        UserEntity userEntity = this.getUserById(1L);
        given(this.userRepository.findByIdWithRoles(1L)).willReturn(Optional.of(this.getUserById(1L)));
        given(this.userWithRolesMapper.toDto(any(UserEntity.class))).willReturn(this.toDtoWithRoles(userEntity));
        // when
        UserWithRolesDTO user = this.service.getUserWithRolesById(1L);
        // than
        Assertions.assertNotNull(user);
    }

    @Test
    public void getUserByUsernameTest() throws WebApiException {
        // given
        UserEntity userEntity = this.getUserByUsername("test1");
        given(this.userRepository.findByUsernameIgnoreCase("test1")).willReturn(Optional.of(this.getUserByUsername("test1")));
        given(this.userMapper.toDto(any(UserEntity.class))).willReturn(this.toDto(userEntity));
        // when
        UserDTO user = this.service.getUserByUsername("test1");
        // than
        Assertions.assertNotNull(user);
    }

    @Test
    void getUserWithRolesByIdExceptionTest() {
        // given
        Long id = 10L;
        given(this.userRepository.findByIdWithRoles(id)).willReturn(Optional.empty());

        // when
        // then
        assertThatThrownBy(() -> {
            this.service.getUserWithRolesById(id);
        }).isInstanceOf(WebApiException.class).hasMessage("USER_NOT_FOUND");
    }

    @Test
    void getUserByUsernameExceptionTest() {
        // given
        String username = "dsad";
        given(this.userRepository.findByUsernameIgnoreCase(username)).willReturn(Optional.empty());

        // when
        // then
        assertThatThrownBy(() -> {
            this.service.getUserByUsername(username);
        }).isInstanceOf(WebApiException.class).hasMessage("USER_NOT_FOUND");
    }

    private UserEntity getUserById(Long id) {
        return this.userData().stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
    }

    private UserEntity getUserByUsername(String username) {
        return this.userData().stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
    }

    public UserEntity toEntity(UserWithRolesDTO dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( dto.getId() );
        userEntity.setName( dto.getName() );
        userEntity.setLastname( dto.getLastname() );
        userEntity.setEmail( dto.getEmail() );
        userEntity.setUsername( dto.getUsername() );
        userEntity.setRoles( roleMapper.toEntityList( dto.getRoles() ) );

        return userEntity;
    }

    public UserWithRolesDTO toDtoWithRoles(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UserWithRolesDTO userWithRolesDTO = new UserWithRolesDTO();

        userWithRolesDTO.setId( entity.getId() );
        userWithRolesDTO.setName( entity.getName() );
        userWithRolesDTO.setLastname( entity.getLastname() );
        userWithRolesDTO.setEmail( entity.getEmail() );
        userWithRolesDTO.setUsername( entity.getUsername() );
        userWithRolesDTO.setRoles( roleMapper.toDtoList( entity.getRoles() ) );

        return userWithRolesDTO;
    }

    private UserDTO toDto(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( entity.getId() );
        userDTO.setCreateDate( entity.getCreateDate() );
        userDTO.setEditDate( entity.getEditDate() );
        userDTO.setName( entity.getName() );
        userDTO.setLastname( entity.getLastname() );
        userDTO.setEmail( entity.getEmail() );
        userDTO.setUsername( entity.getUsername() );
        return userDTO;
    }

    private UserEntity toEntity(UserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( dto.getId() );
        userEntity.setCreateDate( dto.getCreateDate() );
        userEntity.setEditDate( dto.getEditDate() );
        userEntity.setName( dto.getName() );
        userEntity.setLastname( dto.getLastname() );
        userEntity.setEmail( dto.getEmail() );
        userEntity.setUsername( dto.getUsername() );

        return userEntity;
    }

    private List<UserEntity> userData() {
        List<RoleEntity> userRoles = Arrays.asList(new RoleEntity(1L, RoleType.COMMON_USER), new RoleEntity(2L, RoleType.PREMIUM_USER));
        List<UserEntity> userList = new ArrayList<>();
        userList.add(new UserEntity(1L, LocalDateTime.now(), LocalDateTime.now(), "admin",
                "admin", false, "jon1", "james1", "email1@email1.com", "test1", "test123", userRoles));
        userList.add(new UserEntity(2L, LocalDateTime.now(), LocalDateTime.now(), "admin",
                "admin", false, "jon2", "james2", "email1@email2.com", "test2", "test123", userRoles));
        userList.add(new UserEntity(3L, LocalDateTime.now(), LocalDateTime.now(), "admin",
                "admin", false, "jon3", "james3", "email1@email3.com", "test3", "test123", userRoles));
        userList.add(new UserEntity(4L, LocalDateTime.now(), LocalDateTime.now(), "admin",
                "admin", false, "jon4", "james4", "email1@email4.com", "test4", "test123", userRoles));
        userList.add(new UserEntity(5L, LocalDateTime.now(), LocalDateTime.now(), "admin",
                "admin", false, "jon5", "james5", "email1@email5.com", "test5", "test123", userRoles));
        return userList;
    }

}

package com.marcinolek.mytimesheet.service;

import com.marcinolek.mytimesheet.dto.user.UserDTO;
import com.marcinolek.mytimesheet.entity.user.UserEntity;
import com.marcinolek.mytimesheet.exception.WebApiException;
import com.marcinolek.mytimesheet.infrastructure.provider.LoggedUserProvider;
import com.marcinolek.mytimesheet.mapper.base.AbstractMapper;
import com.marcinolek.mytimesheet.repository.base.AbstractExtendedRepository;
import com.marcinolek.mytimesheet.service.base.AbstractCrudService;
import com.marcinolek.mytimesheet.service.user.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest()
public class AbstractCrudServiceOnUserTest {

    @Mock
    private AbstractMapper<UserDTO, UserEntity> mapper;

    @Mock
    private AbstractExtendedRepository<UserEntity, Long> baseExtendedRepository;

    @Mock
    private LoggedUserProvider loggedUserProvider;

    @InjectMocks
    private AbstractCrudService<UserEntity, UserDTO, Long> service = new UserServiceImpl();
//
//    @BeforeEach
//    public void setUp() {
//        when(mapper.toDto(any())).thenReturn(userGroupDTO);
//
//        userGroupDTO.setId(3L);
//        userGroupDTO.setName("group1");
//    }
//
    @Test
    void testFindById() throws WebApiException {
        // given
        Long id = 3L;
        given(this.baseExtendedRepository.findById(id)).willReturn(this.findById(id));
        given(this.mapper.toDto(any())).willReturn(toDto(Objects.requireNonNull(this.findById(id).orElse(null))));
        // when
        UserDTO userDTO = this.service.findById(id);
        // then
        assertThat(userDTO.getId()).isEqualByComparingTo(id);
        assertThat(userDTO).isNotNull();
    }

    @Test
    void testFindByIdException() {
        Long id = 10L;
        given(this.baseExtendedRepository.findById(id)).willReturn(Optional.empty());

        // when
        // then
        assertThatThrownBy(() -> {
            UserDTO dto = this.service.findById(id);
        }).isInstanceOf(WebApiException.class).hasMessage("ENTITY_NOT_FOUND");
        verify(this.baseExtendedRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void testDeleteById() {
        // given
        // when
        Long id = 3L;
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            assertEquals(3L, arg0);
            return null;
        }).when(this.baseExtendedRepository).deleteById(any(Long.class));
        this.baseExtendedRepository.deleteById(id);
        // than
        verify(this.baseExtendedRepository).deleteById(any(Long.class));
    }

    @Test
    void testDeleteByIdException() {
        // given
        Long id = 10L;
        given(this.baseExtendedRepository.findById(id)).willReturn(Optional.empty());

        // when
        // then
        assertThatThrownBy(() -> this.service.deleteById(id)).isInstanceOf(WebApiException.class).hasMessage("DELETE_FAILED");
        verify(this.baseExtendedRepository, times(1)).findById(any(Long.class));
        verify(this.baseExtendedRepository, times(0)).save(any());
    }

    @Test
    void testCreate() throws WebApiException {
        // given
        UserDTO newUser = new UserDTO();
        newUser.setName("TestName");
        newUser.setLastname("TestLastname");
        newUser.setUsername("Test1");
        newUser.setEmail("test@email.test");

        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setName("TestName");
        entity.setLastname("TestLastname");
        entity.setUsername("Test1");
        entity.setEmail("test@email.test");

        given(this.mapper.toEntity(newUser)).willReturn(entity);
        given(this.baseExtendedRepository.save(entity)).willReturn(entity);
        given(this.mapper.toDto(entity)).willReturn(toDto(entity));
        given(this.loggedUserProvider.getUsername()).willReturn(anyString());
        // when
        UserDTO dto = this.service.create(newUser);
        // than
        verify(this.baseExtendedRepository, times(1)).save(any());
        assertThat(entity.getRemoved()).isFalse();
        assertThat(entity.getRemoved()).isNotNull();
        assertThat(dto).isNotNull();
        assertThat(dto.getName()).isEqualTo(entity.getName());
        assertThat(dto.getLastname()).isEqualTo(entity.getLastname());
        assertThat(dto.getUsername()).isEqualTo(entity.getUsername());
        assertThat(dto.getEmail()).isEqualTo(entity.getEmail());
        assertThat(dto.getId()).isEqualTo(entity.getId());
    }

    @Test
    void testUpdate() throws WebApiException {
        // given
        UserDTO newUser = new UserDTO();
        newUser.setId(1L);
        newUser.setName("NewName");
        newUser.setLastname("NewLastname");
        newUser.setUsername("Test1");
        newUser.setEmail("test@email.test");
        newUser.setEditDate(LocalDateTime.now().minusDays(1));

        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setName("TestName");
        entity.setLastname("TestLastname");
        entity.setUsername("Test1");
        entity.setEmail("test@email.test");
        entity.setEditDate(LocalDateTime.now().minusDays(1));

        UserEntity newValuesEntity = new UserEntity();
        newValuesEntity.setId(1L);
        newValuesEntity.setName("NewName");
        newValuesEntity.setLastname("NewLastname");
        newValuesEntity.setUsername("Test1");
        newValuesEntity.setEmail("test@email.test");

        given(this.baseExtendedRepository.findById(1L)).willReturn(Optional.of(entity));
        given(this.mapper.toEntity(any())).willReturn(newValuesEntity);
        given(this.baseExtendedRepository.save(entity)).willReturn(entity);
        given(this.mapper.toDto(any())).willReturn(toDto(newValuesEntity));
        given(this.loggedUserProvider.getUsername()).willReturn(anyString());
        // when
        UserDTO dto = this.service.update(newUser, 1L);
        // than
        verify(this.baseExtendedRepository, times(1)).save(any());
        assertThat(entity.getRemoved()).isFalse();
        assertThat(dto).isNotNull();
        assertThat(entity).isNotNull();
        assertThat(dto.getName()).isEqualTo(entity.getName());
        assertThat(dto.getLastname()).isEqualTo(entity.getLastname());
        assertThat(entity.getName()).isEqualTo("NewName");
        assertThat(entity.getLastname()).isEqualTo("NewLastname");
        assertThat(entity.getId()).isEqualByComparingTo(1L);
        assertThat(entity.getEditDate()).isAfter(newUser.getEditDate());
    }

    private Optional<UserEntity> findById(Long id) {
        return this.userData().stream().filter(x -> x.getId().equals(id)).findFirst();
    }
//
//    private List<UserGroupEntity> deleteById(Long id) {
//        return this.userGroupData().stream().filter(x -> !x.getId().equals(id)).collect(Collectors.toList());
//    }

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
        List<UserEntity> userList = new ArrayList<>();
        userList.add(new UserEntity(1L, LocalDateTime.now(), LocalDateTime.now(), "admin",
                "admin", false, "jon1", "james1", "email1@email1.com", "test1", "test123"));
        userList.add(new UserEntity(2L, LocalDateTime.now(), LocalDateTime.now(), "admin",
                "admin", false, "jon2", "james2", "email1@email2.com", "test2", "test123"));
        userList.add(new UserEntity(3L, LocalDateTime.now(), LocalDateTime.now(), "admin",
                "admin", false, "jon3", "james3", "email1@email3.com", "test3", "test123"));
        userList.add(new UserEntity(4L, LocalDateTime.now(), LocalDateTime.now(), "admin",
                "admin", false, "jon4", "james4", "email1@email4.com", "test4", "test123"));
        userList.add(new UserEntity(5L, LocalDateTime.now(), LocalDateTime.now(), "admin",
                "admin", false, "jon5", "james5", "email1@email5.com", "test5", "test123"));
        return userList;
    }
}

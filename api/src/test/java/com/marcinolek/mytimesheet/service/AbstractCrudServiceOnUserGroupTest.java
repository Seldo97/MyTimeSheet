package com.marcinolek.mytimesheet.service;

import com.marcinolek.mytimesheet.dto.user.UserGroupDTO;
import com.marcinolek.mytimesheet.entity.user.UserGroupEntity;
import com.marcinolek.mytimesheet.exception.WebApiException;
import com.marcinolek.mytimesheet.mapper.base.BaseMapper;
import com.marcinolek.mytimesheet.repository.base.AbstractExtendedRepository;
import com.marcinolek.mytimesheet.service.base.AbstractCrudService;
import com.marcinolek.mytimesheet.service.user.impl.UserGroupServiceImpl;
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
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest()
public class AbstractCrudServiceOnUserGroupTest {

    @Mock
    private BaseMapper<UserGroupDTO, UserGroupEntity> mapper;

    @Mock
    private AbstractExtendedRepository<UserGroupEntity, Long> baseExtendedRepository;

    @InjectMocks
    private AbstractCrudService<UserGroupEntity, UserGroupDTO, Long> service = new UserGroupServiceImpl();

//    @BeforeEach
//    public void setUp() {
//        when(mapper.toDto(any())).thenReturn(userGroupDTO);
//
//        userGroupDTO.setId(3L);
//        userGroupDTO.setName("group1");
//    }

    @Test
    void testFindById() throws WebApiException {
        // given
        Long id = 3L;
        given(this.baseExtendedRepository.findById(id)).willReturn(this.findById(id));
        given(this.mapper.toDto(any())).willReturn(toDto(Objects.requireNonNull(this.findById(id).orElse(null))));
        // when
        UserGroupDTO userGroupDTO = this.service.findById(id);
        // then
        assertThat(userGroupDTO.getId()).isEqualByComparingTo(id);
        assertThat(userGroupDTO).isNotNull();
    }

    @Test
    void testFindByIdException() throws WebApiException {
        Long id = 10L;
        given(this.baseExtendedRepository.findById(id)).willReturn(Optional.empty());

        // when
        // then
        assertThatThrownBy(() -> {
            UserGroupDTO dto = this.service.findById(id);
        }).isInstanceOf(WebApiException.class).hasMessage("ENTITY_NOT_FOUND");
        verify(this.baseExtendedRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void testDeleteById() throws WebApiException {
        // given
        // when
        Long id = 3L;
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            assertEquals(3L, arg0);;
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
        assertThatThrownBy(() -> {
            this.service.deleteById(id);
        }).isInstanceOf(WebApiException.class).hasMessage("DELETE_FAILED");
        verify(this.baseExtendedRepository, times(1)).findById(any(Long.class));
        verify(this.baseExtendedRepository, times(0)).save(any());
    }

    @Test
    void testCreate() throws WebApiException {
        // given
        UserGroupDTO newGroup = new UserGroupDTO();
        newGroup.setName("Test1");

        UserGroupEntity entity = new UserGroupEntity();
        entity.setId(1L);
        entity.setName("Test1");

        given(this.mapper.toEntity(any())).willReturn(entity);
        given(this.baseExtendedRepository.save(entity)).willReturn(entity);
        given(this.mapper.toDto(any())).willReturn(toDto(entity));
        // when
        UserGroupDTO dto = this.service.create(newGroup);
        // than
        verify(this.baseExtendedRepository, times(1)).save(any());
        assertThat(entity.getRemoved()).isFalse();
        assertThat(dto).isNotNull();
        assertThat(dto.getName()).isEqualTo(entity.getName());
    }

    @Test
    void testUpdate() throws WebApiException {
        // given
        UserGroupDTO newGroup = new UserGroupDTO();
        newGroup.setId(1L);
        newGroup.setName("Test2");
        newGroup.setEditDate(LocalDateTime.now().minusDays(1));

        UserGroupEntity entity = new UserGroupEntity();
        entity.setId(1L);
        entity.setName("Test1");
        newGroup.setEditDate(LocalDateTime.now().minusDays(1));

        UserGroupEntity newValuesEntity = new UserGroupEntity();
        newValuesEntity.setId(1L);
        newValuesEntity.setName("Test2");

        given(this.baseExtendedRepository.findById(1L)).willReturn(Optional.of(entity));
        given(this.mapper.toEntity(any())).willReturn(newValuesEntity);
        given(this.baseExtendedRepository.save(entity)).willReturn(entity);
        given(this.mapper.toDto(any())).willReturn(toDto(newValuesEntity));
        // when
        UserGroupDTO dto = this.service.update(newGroup, 1L);
        // than
        verify(this.baseExtendedRepository, times(1)).save(any());
        assertThat(entity.getRemoved()).isFalse();
        assertThat(dto).isNotNull();
        assertThat(entity).isNotNull();
        assertThat(dto.getName()).isEqualTo(entity.getName());
        assertThat(entity.getName()).isEqualTo("Test2");
        assertThat(entity.getId()).isEqualByComparingTo(1L);
        assertThat(entity.getEditDate()).isAfter(newGroup.getEditDate());
    }

    private Optional<UserGroupEntity> findById(Long id) {
        return this.userGroupData().stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    private List<UserGroupEntity> deleteById(Long id) {
        return this.userGroupData().stream().filter(x -> !x.getId().equals(id)).collect(Collectors.toList());
    }

    private UserGroupDTO toDto(UserGroupEntity entity) {
        UserGroupDTO dto = new UserGroupDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEditDate(entity.getEditDate());
        dto.setCreateDate(entity.getCreateDate());
        return dto;
    }

    private UserGroupEntity toEntity(UserGroupDTO dto) {
        UserGroupEntity entity = new UserGroupEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEditDate(dto.getEditDate());
        entity.setCreateDate(dto.getCreateDate());
        return entity;
    }

    private List<UserGroupEntity> userGroupData() {
        List<UserGroupEntity> userGroupList = new ArrayList<>();
        userGroupList.add(new UserGroupEntity(1L, LocalDateTime.now(), LocalDateTime.now(), "admin", "admin", false, "group1"));
        userGroupList.add(new UserGroupEntity(2L, LocalDateTime.now(), LocalDateTime.now(), "admin", "admin", false, "group2"));
        userGroupList.add(new UserGroupEntity(3L, LocalDateTime.now(), LocalDateTime.now(), "admin", "admin", false, "group3"));
        userGroupList.add(new UserGroupEntity(4L, LocalDateTime.now(), LocalDateTime.now(), "admin", "admin", false, "group4"));
        userGroupList.add(new UserGroupEntity(5L, LocalDateTime.now(), LocalDateTime.now(), "admin", "admin", false, "group5"));
        userGroupList.add(new UserGroupEntity(6L, LocalDateTime.now(), LocalDateTime.now(), "admin", "admin", false, "group6"));
        userGroupList.add(new UserGroupEntity(7L, LocalDateTime.now(), LocalDateTime.now(), "admin", "admin", false, "group7"));
        return userGroupList;
    }
}

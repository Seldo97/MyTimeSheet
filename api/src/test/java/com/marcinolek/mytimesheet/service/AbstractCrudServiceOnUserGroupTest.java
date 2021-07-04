package com.marcinolek.mytimesheet.service;

import com.marcinolek.mytimesheet.constants.exception.WebApiExceptionType;
import com.marcinolek.mytimesheet.dto.user.UserGroupDTO;
import com.marcinolek.mytimesheet.entity.user.UserGroupEntity;
import com.marcinolek.mytimesheet.exception.WebApiException;
import com.marcinolek.mytimesheet.mapper.base.BaseMapper;
import com.marcinolek.mytimesheet.repository.base.AbstractExtendedRepository;
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

import static org.assertj.core.api.Assertions.*;
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
    private AbstractExtendedRepository<UserGroupEntity, Long> baseRepository;

    @InjectMocks
    private UserGroupServiceImpl service;


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
        given(this.baseRepository.findById(id)).willReturn(this.findById(id));
        given(this.mapper.toDto(any())).willReturn(toDto(Objects.requireNonNull(this.findById(id).orElse(null))));
        // when
        UserGroupDTO userGroupDTO = this.service.findById(id);
        // then
        assertThat(userGroupDTO.getId()).isEqualByComparingTo(id);
        assertThat(userGroupDTO).isNotNull();
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
        }).when(this.baseRepository).deleteById(any(Long.class));
        this.baseRepository.deleteById(id);
        // than
//        verify(this.baseRepository).deleteById(any(Long.class));
//        verify(this.baseRepository).findById(any(Long.class));
    }

    @Test
    void testDeleteByIdException() {
        // given
        Long id = 10L;
        // when
        // then
        assertThat(catchThrowable(() -> { throw new WebApiException(WebApiExceptionType.DELETE_FAILED); }))
                .isInstanceOf(WebApiException.class)
                .hasMessage("DELETE_FAILED");
//        verify(this.baseRepository, times(1)).findById(anyLong());
        verify(this.baseRepository, times(0)).save(any(UserGroupEntity.class));
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

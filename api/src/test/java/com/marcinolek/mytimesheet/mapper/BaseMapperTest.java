package com.marcinolek.mytimesheet.mapper;

import com.marcinolek.mytimesheet.dto.user.UserDTO;
import com.marcinolek.mytimesheet.dto.user.UserGroupDTO;
import com.marcinolek.mytimesheet.entity.user.UserEntity;
import com.marcinolek.mytimesheet.entity.user.UserGroupEntity;
import com.marcinolek.mytimesheet.mapper.base.BaseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest()
@ContextConfiguration
public class BaseMapperTest {

    @Autowired
    private BaseMapper<UserGroupDTO, UserGroupEntity> userGroupMapper;

    @Autowired
    private BaseMapper<UserDTO, UserEntity> userMapper;

    @Test
    public void testUserGroupToDto() {
        UserGroupEntity entity = new UserGroupEntity();
        entity.setId(1L);
        entity.setName("testGroup");
        entity.setCreateDate(LocalDateTime.now());
        entity.setEditDate(LocalDateTime.now());
        entity.setCreatedBy("admin");
        entity.setEditedBy("admin");
        entity.setRemoved(false);

        UserGroupDTO dto = userGroupMapper.toDto(entity);

        assertThat(dto.getId()).isEqualByComparingTo(entity.getId());
        assertThat(dto.getCreateDate()).isEqualTo(entity.getCreateDate());
        assertThat(dto.getEditDate()).isEqualTo(entity.getEditDate());
        assertThat(dto.getName()).isEqualTo(entity.getName());
    }

    @Test
    public void testUserGroupToEntity() {
        UserGroupDTO dto = new UserGroupDTO();
        dto.setId(1L);
        dto.setName("testGroup");
        dto.setCreateDate(LocalDateTime.now());
        dto.setEditDate(LocalDateTime.now());

        UserGroupEntity entity = userGroupMapper.toEntity(dto);

        assertThat(entity.getId()).isEqualByComparingTo(dto.getId());
        assertThat(entity.getName()).isEqualTo(dto.getName());
        assertThat(entity.getCreateDate()).isEqualTo(dto.getCreateDate());
        assertThat(entity.getEditDate()).isEqualTo(dto.getEditDate());
    }

    @Test
    public void testUserToDto() {
        UserGroupEntity g1 = new UserGroupEntity();
        g1.setId(1L);
        g1.setName("g1");
        UserGroupEntity g2 = new UserGroupEntity();
        g2.setId(2L);
        g2.setName("g2");
        List<UserGroupEntity> userGroupEntities = new ArrayList<>() {{
            add(g1);
            add(g2);
        }};
        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setName("Name");
        entity.setLastname("Lastname");
        entity.setEmail("exmp@exmp.com");
        entity.setUsername("Username");
        entity.setCreateDate(LocalDateTime.now());
        entity.setUserGroups(userGroupEntities);

        UserDTO dto = userMapper.toDto(entity);

        assertThat(entity.getId()).isEqualByComparingTo(dto.getId());
        assertThat(entity.getName()).isEqualTo(dto.getName());
        assertThat(entity.getEmail()).isEqualTo(dto.getEmail());
        assertThat(entity.getLastname()).isEqualTo(dto.getLastname());
        assertThat(entity.getUsername()).isEqualTo(dto.getUsername());
    }

}

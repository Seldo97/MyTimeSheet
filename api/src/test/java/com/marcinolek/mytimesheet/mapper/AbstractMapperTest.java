package com.marcinolek.mytimesheet.mapper;

import com.marcinolek.mytimesheet.dto.user.UserDTO;
import com.marcinolek.mytimesheet.entity.user.UserEntity;
import com.marcinolek.mytimesheet.mapper.base.AbstractMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest()
@ContextConfiguration
public class AbstractMapperTest {

    @Autowired
    private AbstractMapper<UserDTO, UserEntity> userMapper;

    @Test
    public void testUserEntityToDto() {
        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setName("Martin");
        entity.setLastname("Jon");
        entity.setUsername("tester");
        entity.setCreateDate(LocalDateTime.now());
        entity.setEditDate(LocalDateTime.now());
        entity.setCreatedBy("admin");
        entity.setEditedBy("admin");
        entity.setRemoved(false);

        UserDTO dto = userMapper.toDto(entity);

        assertThat(dto.getId()).isEqualByComparingTo(entity.getId());
        assertThat(dto.getCreateDate()).isEqualTo(entity.getCreateDate());
        assertThat(dto.getEditDate()).isEqualTo(entity.getEditDate());
        assertThat(dto.getName()).isEqualTo(entity.getName());
        assertThat(dto.getLastname()).isEqualTo(entity.getLastname());
        assertThat(dto.getUsername()).isEqualTo(entity.getUsername());
    }

    @Test
    public void testUserDtoToEntity() {
        UserDTO dto = new UserDTO();
        dto.setId(1L);
        dto.setName("Martin");
        dto.setLastname("Jon");
        dto.setUsername("tester");
        dto.setCreateDate(LocalDateTime.now());
        dto.setEditDate(LocalDateTime.now());

        UserEntity entity = userMapper.toEntity(dto);

        assertThat(dto.getId()).isEqualByComparingTo(entity.getId());
        assertThat(dto.getCreateDate()).isEqualTo(entity.getCreateDate());
        assertThat(dto.getEditDate()).isEqualTo(entity.getEditDate());
        assertThat(dto.getName()).isEqualTo(entity.getName());
        assertThat(dto.getLastname()).isEqualTo(entity.getLastname());
        assertThat(dto.getUsername()).isEqualTo(entity.getUsername());
    }

}

package com.marcinolek.mytimesheet.mapper;

import com.marcinolek.mytimesheet.dto.user.UserGroupDTO;
import com.marcinolek.mytimesheet.entity.user.UserGroupEntity;
import com.marcinolek.mytimesheet.mapper.base.BaseMapper;
import com.marcinolek.mytimesheet.mapper.user.UserGroupMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
//@SpringBootTest(classes = { UserGroupMapperImpl.class })
public class BaseMapperTest {

    @Autowired
    private BaseMapper<UserGroupDTO, UserGroupEntity> userGroupMapper;

    @Test
    public void testUserGroupToEntity() {
        UserGroupEntity entity = new UserGroupEntity();
        entity.setId(1L);
        entity.setName("testGroup");

        UserGroupDTO dto = userGroupMapper.toDto(entity);

        assertThat(dto.getId()).isEqualByComparingTo(entity.getId());
        assertThat(dto.getName()).isEqualTo(entity.getName());
    }

    @Test
    public void testUserGroupToDto() {
        UserGroupDTO dto = new UserGroupDTO();
        dto.setId(1L);
        dto.setName("testGroup");

        UserGroupEntity entity = userGroupMapper.toEntity(dto);

        assertThat(entity.getId()).isEqualByComparingTo(dto.getId());
        assertThat(entity.getName()).isEqualTo(dto.getName());
    }

}

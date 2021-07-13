package com.marcinolek.mytimesheet.service.user;

import com.marcinolek.mytimesheet.dto.user.UserGroupDTO;
import com.marcinolek.mytimesheet.entity.user.UserGroupEntity;
import com.marcinolek.mytimesheet.service.base.AbstractCrudExtendedService;

import java.util.List;

public interface UserGroupService extends AbstractCrudExtendedService<UserGroupEntity, UserGroupDTO, Long> {

    List<UserGroupDTO> getUserGroupsByUserId(Long id);

}

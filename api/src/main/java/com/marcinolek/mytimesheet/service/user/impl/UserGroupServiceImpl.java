package com.marcinolek.mytimesheet.service.user.impl;

import com.marcinolek.mytimesheet.dto.user.UserGroupDTO;
import com.marcinolek.mytimesheet.entity.user.UserGroupEntity;
import com.marcinolek.mytimesheet.repository.user.UserGroupRepository;
import com.marcinolek.mytimesheet.service.base.impl.AbstractCrudExtendedServiceImpl;
import com.marcinolek.mytimesheet.service.user.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGroupServiceImpl extends AbstractCrudExtendedServiceImpl<UserGroupEntity, UserGroupDTO, Long>
        implements UserGroupService {

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Override
    public List<UserGroupDTO> getUserGroupsByUserId(Long id) {
        return this.mapper.toDtoList(userGroupRepository.getUserGroupsByUserId(id));
    }
}

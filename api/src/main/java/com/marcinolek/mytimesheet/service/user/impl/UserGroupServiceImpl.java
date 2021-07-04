package com.marcinolek.mytimesheet.service.user.impl;

import com.marcinolek.mytimesheet.dto.user.UserGroupDTO;
import com.marcinolek.mytimesheet.entity.user.UserGroupEntity;
import com.marcinolek.mytimesheet.service.base.impl.AbstractCrudExtendedServiceImpl;
import com.marcinolek.mytimesheet.service.user.UserGroupService;
import org.springframework.stereotype.Service;

@Service
public class UserGroupServiceImpl extends AbstractCrudExtendedServiceImpl<UserGroupEntity, UserGroupDTO, Long>
        implements UserGroupService {

}

package com.marcinolek.mytimesheet.service.workspace.impl;

import com.marcinolek.mytimesheet.dto.workspace.UserWorkspaceDTO;
import com.marcinolek.mytimesheet.entity.workspace.UserWorkspaceEntity;
import com.marcinolek.mytimesheet.service.base.impl.AbstractCrudServiceImpl;
import com.marcinolek.mytimesheet.service.workspace.UserWorkspaceService;
import org.springframework.stereotype.Service;

@Service
public class UserWorkspaceImpl extends AbstractCrudServiceImpl<UserWorkspaceEntity, UserWorkspaceDTO, Long>
        implements UserWorkspaceService {
}

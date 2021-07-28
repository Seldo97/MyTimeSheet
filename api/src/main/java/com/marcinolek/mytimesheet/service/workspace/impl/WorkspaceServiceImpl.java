package com.marcinolek.mytimesheet.service.workspace.impl;

import com.marcinolek.mytimesheet.dto.workspace.WorkspaceDTO;
import com.marcinolek.mytimesheet.entity.workspace.WorkspaceEntity;
import com.marcinolek.mytimesheet.service.base.impl.AbstractCrudServiceImpl;
import com.marcinolek.mytimesheet.service.workspace.WorkspaceService;
import org.springframework.stereotype.Service;

@Service
public class WorkspaceServiceImpl extends AbstractCrudServiceImpl<WorkspaceEntity, WorkspaceDTO, Long> implements WorkspaceService {
}

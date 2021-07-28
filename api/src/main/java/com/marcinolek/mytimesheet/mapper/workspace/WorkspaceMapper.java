package com.marcinolek.mytimesheet.mapper.workspace;

import com.marcinolek.mytimesheet.dto.workspace.WorkspaceDTO;
import com.marcinolek.mytimesheet.entity.workspace.WorkspaceEntity;
import com.marcinolek.mytimesheet.mapper.base.AbstractMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkspaceMapper extends AbstractMapper<WorkspaceDTO, WorkspaceEntity> {
}

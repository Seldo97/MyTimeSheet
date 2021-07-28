package com.marcinolek.mytimesheet.mapper.workspace;

import com.marcinolek.mytimesheet.dto.workspace.UserWorkspaceDTO;
import com.marcinolek.mytimesheet.entity.workspace.UserWorkspaceEntity;
import com.marcinolek.mytimesheet.mapper.base.AbstractMapper;
import com.marcinolek.mytimesheet.mapper.user.UserMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, WorkspaceMapper.class})
public interface UserWorkspaceMapper extends AbstractMapper<UserWorkspaceDTO, UserWorkspaceEntity> {
}

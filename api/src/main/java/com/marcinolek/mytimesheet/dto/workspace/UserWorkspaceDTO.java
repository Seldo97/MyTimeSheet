package com.marcinolek.mytimesheet.dto.workspace;

import com.marcinolek.mytimesheet.constants.permission.PermissionType;
import com.marcinolek.mytimesheet.dto.base.AbstractDTO;
import com.marcinolek.mytimesheet.dto.user.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserWorkspaceDTO extends AbstractDTO {

    private UserDTO user;

    private WorkspaceDTO workspace;

    private PermissionType permissionType;
}

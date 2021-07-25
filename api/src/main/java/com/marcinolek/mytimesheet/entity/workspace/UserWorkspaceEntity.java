package com.marcinolek.mytimesheet.entity.workspace;

import com.marcinolek.mytimesheet.constants.permission.PermissionType;
import com.marcinolek.mytimesheet.entity.base.AbstractEntity;
import com.marcinolek.mytimesheet.entity.user.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserWorkspaceEntity extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id")
    private WorkspaceEntity workspace;

    @Enumerated(EnumType.STRING)
    private PermissionType permissionType;

}

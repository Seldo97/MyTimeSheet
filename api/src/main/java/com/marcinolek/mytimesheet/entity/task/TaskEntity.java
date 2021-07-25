package com.marcinolek.mytimesheet.entity.task;

import com.marcinolek.mytimesheet.entity.base.AbstractEntity;
import com.marcinolek.mytimesheet.entity.workspace.WorkspaceEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class TaskEntity extends AbstractEntity {

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id")
    private WorkspaceEntity workspace;

}

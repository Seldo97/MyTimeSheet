package com.marcinolek.mytimesheet.entity.workspace;

import com.marcinolek.mytimesheet.entity.base.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class WorkspaceEntity extends AbstractEntity {

    private String name;

}

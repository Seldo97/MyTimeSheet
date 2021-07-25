package com.marcinolek.mytimesheet.entity.tag;

import com.marcinolek.mytimesheet.entity.base.AbstractEntity;
import com.marcinolek.mytimesheet.entity.task.TaskEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class TagEntity extends AbstractEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private TaskEntity task;

}

package com.marcinolek.mytimesheet.service.task;

import com.marcinolek.mytimesheet.dto.task.TaskDTO;
import com.marcinolek.mytimesheet.entity.task.TaskEntity;
import com.marcinolek.mytimesheet.service.base.AbstractCrudService;

public interface TaskService extends AbstractCrudService<TaskEntity, TaskDTO, Long> {
}

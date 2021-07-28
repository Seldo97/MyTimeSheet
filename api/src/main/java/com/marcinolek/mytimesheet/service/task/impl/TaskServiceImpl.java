package com.marcinolek.mytimesheet.service.task.impl;

import com.marcinolek.mytimesheet.dto.task.TaskDTO;
import com.marcinolek.mytimesheet.entity.task.TaskEntity;
import com.marcinolek.mytimesheet.service.base.impl.AbstractCrudServiceImpl;
import com.marcinolek.mytimesheet.service.task.TaskService;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl extends AbstractCrudServiceImpl<TaskEntity, TaskDTO, Long> implements TaskService {
}

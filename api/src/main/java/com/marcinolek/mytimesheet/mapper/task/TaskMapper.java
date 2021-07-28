package com.marcinolek.mytimesheet.mapper.task;

import com.marcinolek.mytimesheet.dto.task.TaskDTO;
import com.marcinolek.mytimesheet.entity.task.TaskEntity;
import com.marcinolek.mytimesheet.mapper.base.AbstractMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper extends AbstractMapper<TaskDTO, TaskEntity> {
}

package com.marcinolek.mytimesheet.repository.task;

import com.marcinolek.mytimesheet.entity.task.TaskEntity;
import com.marcinolek.mytimesheet.repository.base.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends AbstractRepository<TaskEntity, Long> {
}

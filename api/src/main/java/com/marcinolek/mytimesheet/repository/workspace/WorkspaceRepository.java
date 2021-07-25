package com.marcinolek.mytimesheet.repository.workspace;

import com.marcinolek.mytimesheet.entity.workspace.WorkspaceEntity;
import com.marcinolek.mytimesheet.repository.base.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceRepository extends AbstractRepository<WorkspaceEntity, Long> {
}

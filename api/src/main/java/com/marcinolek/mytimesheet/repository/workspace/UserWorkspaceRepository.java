package com.marcinolek.mytimesheet.repository.workspace;

import com.marcinolek.mytimesheet.entity.workspace.UserWorkspaceEntity;
import com.marcinolek.mytimesheet.repository.base.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWorkspaceRepository extends AbstractRepository<UserWorkspaceEntity, Long> {
}

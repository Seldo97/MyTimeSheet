package com.marcinolek.mytimesheet.repository.user;

import com.marcinolek.mytimesheet.entity.user.PermissionEntity;
import com.marcinolek.mytimesheet.repository.base.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PermissionRepository extends AbstractRepository<PermissionEntity, Long> {

    @Query("SELECT g.permissions FROM UserGroupEntity g INNER JOIN g.users u WHERE u.id = :id")
    Set<PermissionEntity> getPermissionsByUserId(Long id);

}

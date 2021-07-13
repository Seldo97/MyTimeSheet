package com.marcinolek.mytimesheet.repository.user;

import com.marcinolek.mytimesheet.entity.user.UserGroupEntity;
import com.marcinolek.mytimesheet.repository.base.AbstractExtendedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupRepository extends AbstractExtendedRepository<UserGroupEntity, Long> {

    @Query(value = "SELECT g FROM UserGroupEntity g INNER JOIN g.users u WHERE u.id = :id")
    List<UserGroupEntity> getUserGroupsByUserId(@Param("id") Long id);

}
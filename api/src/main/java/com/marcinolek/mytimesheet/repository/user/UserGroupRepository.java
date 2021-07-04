package com.marcinolek.mytimesheet.repository.user;

import com.marcinolek.mytimesheet.entity.user.UserGroupEntity;
import com.marcinolek.mytimesheet.repository.base.AbstractExtendedRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends AbstractExtendedRepository<UserGroupEntity, Long> {
}
package com.marcinolek.mytimesheet.repository.user;

import com.marcinolek.mytimesheet.entity.user.UserGroupEntity;
import com.marcinolek.mytimesheet.repository.base.BaseExtendedRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends BaseExtendedRepository<UserGroupEntity, Long> {
}
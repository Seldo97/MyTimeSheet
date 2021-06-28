package com.marcinolek.mytimesheet.repository.user;

import com.marcinolek.mytimesheet.entity.user.UserEntity;
import com.marcinolek.mytimesheet.repository.base.BaseExtendedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends BaseExtendedRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.userGroups WHERE u.removed IS NULL OR u.removed = false")
    List<UserEntity> findAllWithGroups();

    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.userGroups WHERE u.id = :id " +
            "AND (u.removed IS NULL OR u.removed = false)")
    Optional<UserEntity> findByIdWithGroups(@Param("id") Long id);

    Optional<UserEntity> findByUsernameIgnoreCase(String username);
}
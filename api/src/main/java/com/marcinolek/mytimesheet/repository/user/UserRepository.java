package com.marcinolek.mytimesheet.repository.user;

import com.marcinolek.mytimesheet.entity.user.UserEntity;
import com.marcinolek.mytimesheet.repository.base.AbstractExtendedRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends AbstractExtendedRepository<UserEntity, Long> {

    @EntityGraph(attributePaths = "roles")
    @Query("SELECT u FROM UserEntity u WHERE u.id = ?1 " +
            "AND (u.removed IS NULL OR u.removed = false)")
    Optional<UserEntity> findByIdWithRoles(Long id);

    Optional<UserEntity> findByUsernameIgnoreCase(String username);
}
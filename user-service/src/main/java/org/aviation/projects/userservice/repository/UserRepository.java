package org.aviation.projects.userservice.repository;

import org.aviation.projects.userservice.entity.User;
import org.aviation.projects.userservice.entity.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    List<User> findAllByRolesContainsAndDeletedFalseAndEmailIsNotNull(ERole role);
}

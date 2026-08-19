package com.bautistagaber.connectiontoswapi.infrastructure.adapter.postgress;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Spring Data JPA repository for UserEntity. Extends JpaRepository and adds search by username.
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}

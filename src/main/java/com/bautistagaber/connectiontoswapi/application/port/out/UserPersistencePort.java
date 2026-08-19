package com.bautistagaber.connectiontoswapi.application.port.out;

import com.bautistagaber.connectiontoswapi.domain.user.User;

import java.util.Optional;

/**
 * Outbound port for user persistence: save and find by username.
 * Implemented by UserRepositoryAdapter in the infrastructure layer.
 */
public interface UserPersistencePort {
    User save(User user);

    Optional<User> findByUsername(String username);
}

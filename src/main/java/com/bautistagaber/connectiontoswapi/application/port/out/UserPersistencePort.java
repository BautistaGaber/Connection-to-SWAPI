package com.bautistagaber.connectiontoswapi.application.port.out;

import com.bautistagaber.connectiontoswapi.domain.user.User;

import java.util.Optional;

public interface UserPersistencePort {
    User save(User user);

    Optional<User> findByUsername(String username);
}

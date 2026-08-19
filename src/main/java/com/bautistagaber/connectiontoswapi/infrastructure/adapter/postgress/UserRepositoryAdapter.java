package com.bautistagaber.connectiontoswapi.infrastructure.adapter.postgress;

import com.bautistagaber.connectiontoswapi.application.port.out.UserPersistencePort;
import com.bautistagaber.connectiontoswapi.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Adapter implementing UserPersistencePort. Maps between the domain User model
 * and the JPA UserEntity, delegating database operations to UserRepository.
 */
@Component
public class UserRepositoryAdapter implements UserPersistencePort {
    private final UserRepository userRepository;

    public UserRepositoryAdapter(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {

        UserEntity entity = UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .build();

        UserEntity savedEntity = userRepository.save(entity);

        return User.builder()
                .id(savedEntity.getId())
                .username(savedEntity.getUsername())
                .password(savedEntity.getPassword())
                .role(user.getRole())
                .build();
    }

    @Override
    public Optional<User> findByUsername(String username) {

        return userRepository.findByUsername(username)
                .map(entity -> User.builder()
                        .id(entity.getId())
                        .username(entity.getUsername())
                        .password(entity.getPassword())
                        .role(entity.getRole())
                        .build());
    }
}

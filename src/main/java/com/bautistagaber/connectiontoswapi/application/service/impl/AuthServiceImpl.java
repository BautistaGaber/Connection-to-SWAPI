package com.bautistagaber.connectiontoswapi.application.service.impl;

import com.bautistagaber.connectiontoswapi.application.command.LoginCommand;
import com.bautistagaber.connectiontoswapi.application.command.RegisterCommand;
import com.bautistagaber.connectiontoswapi.application.exception.InvalidCredentialsException;
import com.bautistagaber.connectiontoswapi.application.exception.UserAlreadyExistsException;
import com.bautistagaber.connectiontoswapi.application.port.out.JwtPort;
import com.bautistagaber.connectiontoswapi.application.port.out.PasswordEncoderPort;
import com.bautistagaber.connectiontoswapi.application.port.out.UserPersistencePort;
import com.bautistagaber.connectiontoswapi.application.service.AuthService;
import com.bautistagaber.connectiontoswapi.domain.user.Role;
import com.bautistagaber.connectiontoswapi.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserPersistencePort userPersistencePort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final JwtPort jwtPort;

    @Override
    public User register(RegisterCommand command) {

        Optional<User> existingUser =
                userPersistencePort.findByUsername(command.username());

        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("Username already exists");
        }

        String encodedPassword =
                passwordEncoderPort.encode(command.password());

        User user = User.builder()
                .username(command.username())
                .password(encodedPassword)
                .role(Role.USER)
                .build();

        return userPersistencePort.save(user);
    }

    @Override
    public String login(LoginCommand command) {
        User user = userPersistencePort.findByUsername(command.username())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));

        boolean passwordMatches = passwordEncoderPort.matches(command.password(), user.getPassword());

        if(!passwordMatches){
            throw new InvalidCredentialsException("Invalid credentials");
        }

        return jwtPort.generateToken(user);
    }
}

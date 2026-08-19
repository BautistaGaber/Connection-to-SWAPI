package com.bautistagaber.connectiontoswapi.application.service.impl;

import com.bautistagaber.connectiontoswapi.application.command.LoginCommand;
import com.bautistagaber.connectiontoswapi.application.command.RegisterCommand;
import com.bautistagaber.connectiontoswapi.application.exception.InvalidCredentialsException;
import com.bautistagaber.connectiontoswapi.application.exception.UserAlreadyExistsException;
import com.bautistagaber.connectiontoswapi.application.port.out.JwtPort;
import com.bautistagaber.connectiontoswapi.application.port.out.PasswordEncoderPort;
import com.bautistagaber.connectiontoswapi.application.port.out.UserPersistencePort;
import com.bautistagaber.connectiontoswapi.domain.user.Role;
import com.bautistagaber.connectiontoswapi.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserPersistencePort userPersistencePort;

    @Mock
    private PasswordEncoderPort passwordEncoderPort;

    @Mock
    private JwtPort jwtPort;

    @InjectMocks
    private AuthServiceImpl authService;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .username("bautista")
                .password("encodedPassword123")
                .role(Role.USER)
                .build();
    }

    @Test
    void register_withValidData_returnsSavedUser() {
        RegisterCommand command = new RegisterCommand("bautista", "password123");

        when(userPersistencePort.findByUsername("bautista")).thenReturn(Optional.empty());
        when(passwordEncoderPort.encode("password123")).thenReturn("encodedPassword123");
        when(userPersistencePort.save(any(User.class))).thenReturn(user);

        User result = authService.register(command);

        assertNotNull(result);
        assertEquals("bautista", result.getUsername());
        assertEquals(Role.USER, result.getRole());
        verify(userPersistencePort).findByUsername("bautista");
        verify(passwordEncoderPort).encode("password123");
        verify(userPersistencePort).save(any(User.class));
    }

    @Test
    void register_withDuplicateUsername_throwsUserAlreadyExistsException() {
        RegisterCommand command = new RegisterCommand("bautista", "password123");

        when(userPersistencePort.findByUsername("bautista")).thenReturn(Optional.of(user));

        assertThrows(UserAlreadyExistsException.class, () -> authService.register(command));
        verify(userPersistencePort, never()).save(any());
    }

    @Test
    void login_withValidCredentials_returnsToken() {
        LoginCommand command = new LoginCommand("bautista", "password123");

        when(userPersistencePort.findByUsername("bautista")).thenReturn(Optional.of(user));
        when(passwordEncoderPort.matches("password123", "encodedPassword123")).thenReturn(true);
        when(jwtPort.generateToken(user)).thenReturn("jwt-token-abc123");

        String token = authService.login(command);

        assertEquals("jwt-token-abc123", token);
        verify(jwtPort).generateToken(user);
    }

    @Test
    void login_withWrongPassword_throwsInvalidCredentialsException() {
        LoginCommand command = new LoginCommand("bautista", "wrongpassword");

        when(userPersistencePort.findByUsername("bautista")).thenReturn(Optional.of(user));
        when(passwordEncoderPort.matches("wrongpassword", "encodedPassword123")).thenReturn(false);

        assertThrows(InvalidCredentialsException.class, () -> authService.login(command));
        verify(jwtPort, never()).generateToken(any());
    }

    @Test
    void login_withNonExistentUsername_throwsInvalidCredentialsException() {
        LoginCommand command = new LoginCommand("nonexistent", "password123");

        when(userPersistencePort.findByUsername("nonexistent")).thenReturn(Optional.empty());

        assertThrows(InvalidCredentialsException.class, () -> authService.login(command));
    }
}

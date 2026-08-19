package com.bautistagaber.connectiontoswapi.presentation.controller;

import com.bautistagaber.connectiontoswapi.application.exception.InvalidCredentialsException;
import com.bautistagaber.connectiontoswapi.application.exception.UserAlreadyExistsException;
import com.bautistagaber.connectiontoswapi.application.port.out.JwtPort;
import com.bautistagaber.connectiontoswapi.application.port.out.UserPersistencePort;
import com.bautistagaber.connectiontoswapi.application.service.AuthService;
import com.bautistagaber.connectiontoswapi.domain.user.Role;
import com.bautistagaber.connectiontoswapi.domain.user.User;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.LoginRequest;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.RegisterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private JwtPort jwtPort;

    @MockitoBean
    private UserPersistencePort userPersistencePort;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void register_withValidData_returns201() throws Exception {
        User user = User.builder().id(1L).username("bautista").role(Role.USER).build();

        when(authService.register(any())).thenReturn(user);

        RegisterRequest request = new RegisterRequest("bautista", "password123");

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("bautista"));
    }

    @Test
    void register_withDuplicateUsername_returns409() throws Exception {
        when(authService.register(any())).thenThrow(new UserAlreadyExistsException("Username already exists"));

        RegisterRequest request = new RegisterRequest("bautista", "password123");

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.error").value("Conflict"))
                .andExpect(jsonPath("$.message").value("Username already exists"));
    }

    @Test
    void login_withValidCredentials_returns200WithToken() throws Exception {
        when(authService.login(any())).thenReturn("jwt-token-abc123");

        LoginRequest request = new LoginRequest("bautista", "password123");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("jwt-token-abc123"));
    }

    @Test
    void login_withWrongCredentials_returns401() throws Exception {
        when(authService.login(any())).thenThrow(new InvalidCredentialsException("Invalid credentials"));

        LoginRequest request = new LoginRequest("bautista", "wrongpassword");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status").value(401))
                .andExpect(jsonPath("$.error").value("Unauthorized"))
                .andExpect(jsonPath("$.message").value("Invalid credentials"));
    }
}

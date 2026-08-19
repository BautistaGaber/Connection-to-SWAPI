package com.bautistagaber.connectiontoswapi.presentation.controller;

import com.bautistagaber.connectiontoswapi.application.command.LoginCommand;
import com.bautistagaber.connectiontoswapi.application.command.RegisterCommand;
import com.bautistagaber.connectiontoswapi.application.service.AuthService;
import com.bautistagaber.connectiontoswapi.domain.user.User;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.LoginRequest;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.LoginResponse;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.RegisterRequest;
import com.bautistagaber.connectiontoswapi.presentation.dto.response.RegisterResponse;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {

        RegisterCommand command = new RegisterCommand(request.username(), request.password());
        User user = authService.register(command);
        RegisterResponse response = new RegisterResponse(user.getId(), user.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //es keysensitive

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){

        LoginCommand command = new LoginCommand(request.username(), request.password());
        String token = authService.login(command);
        LoginResponse response = new LoginResponse(token);
        return ResponseEntity.ok(response);
    }
}

package com.bautistagaber.connectiontoswapi.application.service;

import com.bautistagaber.connectiontoswapi.application.command.LoginCommand;
import com.bautistagaber.connectiontoswapi.application.command.RegisterCommand;
import com.bautistagaber.connectiontoswapi.domain.user.User;

/**
 * Service interface for authentication: user registration and login.
 */
public interface AuthService {
    User register(RegisterCommand command);

    String login(LoginCommand command);
}

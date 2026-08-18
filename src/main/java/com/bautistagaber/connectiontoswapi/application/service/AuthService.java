package com.bautistagaber.connectiontoswapi.application.service;

import com.bautistagaber.connectiontoswapi.application.command.LoginCommand;
import com.bautistagaber.connectiontoswapi.application.command.RegisterCommand;
import com.bautistagaber.connectiontoswapi.domain.user.User;

public interface AuthService {
    User register(RegisterCommand command);

    String login(LoginCommand command);
}

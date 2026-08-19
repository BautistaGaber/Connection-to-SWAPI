package com.bautistagaber.connectiontoswapi.application.port.out;

/**
 * Outbound port for password encoding and matching.
 * Implemented by BCryptPasswordEncoderAdapter in the infrastructure layer.
 */
public interface PasswordEncoderPort {
    String encode(String password);

    boolean matches(String rawPassword, String encodedPassword);
}

package com.bautistagaber.connectiontoswapi.domain.user;

import lombok.*;

/**
 * Domain model representing an application user.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private Role role;
}

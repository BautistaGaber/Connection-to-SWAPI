package com.bautistagaber.connectiontoswapi.infrastructure.adapter.postgress;

import com.bautistagaber.connectiontoswapi.domain.user.Role;
import jakarta.persistence.*;
import lombok.*;

/**
 * JPA entity mapping the 'users' table in PostgreSQL.
 * Stores id, username, password, and role.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}

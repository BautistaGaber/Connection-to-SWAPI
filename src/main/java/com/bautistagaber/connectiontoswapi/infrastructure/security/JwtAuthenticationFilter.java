package com.bautistagaber.connectiontoswapi.infrastructure.security;

import com.bautistagaber.connectiontoswapi.application.port.out.JwtPort;
import com.bautistagaber.connectiontoswapi.application.port.out.UserPersistencePort;
import com.bautistagaber.connectiontoswapi.domain.user.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtPort jwtPort;
    private final UserPersistencePort userPersistencePort;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null ||
                !authorizationHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }
        String token = authorizationHeader.substring(7);

        try {

            String username = jwtPort.extractUsername(token);

            Optional<User> userOptional = userPersistencePort.findByUsername(username);

            if (userOptional.isPresent()) {
                User user = userOptional.get();

                if (jwtPort.isTokenValid(token, user)) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(user.getUsername(), new SimpleGrantedAuthority("ROLE_" + user.getRole().name()), Collections.emptyList());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

        } catch (Exception exception) {
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
    }
}

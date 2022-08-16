package com.pentyugov.wflow.workflowservice.core.system.security;


import com.pentyugov.wflow.workflowservice.core.system.application.User;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            authenticate(request);
        } catch (RuntimeException ex) {
            log.error("Authentication failed", ex);
        }
        filterChain.doFilter(request, response);
    }

    private void authenticate(HttpServletRequest request) {
        var authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null) {

            var authenticationToken = getAuthenticationToken(authorizationHeader);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext()
                    .setAuthentication(authenticationToken);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String authorizationHeader) {
        var jwt = authorizationHeader.replace("Bearer ", "");

        var claims = jwtProvider.getClaims(jwt);
        Collection<SimpleGrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(buildUser(claims), null, authorities);
    }

    private User buildUser(Claims claims) {
        Set<User.Role> roles = Arrays.stream(claims.get("roles").toString().split(","))
                .map(User.Role::new)
                .collect(Collectors.toSet());

        return User.builder()
                .id(UUID.fromString(claims.getSubject()))
                .username((String) claims.get("username"))
                .roles(roles)
                .build();
    }

}

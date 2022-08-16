package com.pentyugov.wflow.workflowservice.core.system.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    @NotEmpty
    @Value("${jwt.secret}")
    private String secret;

    public Claims getClaims(String jwt) {

        return Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwt).getBody();
    }

}

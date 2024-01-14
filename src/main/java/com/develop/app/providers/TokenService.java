package com.develop.app.providers;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.develop.app.modules.users.types.UserRole;

@Service
public class TokenService {
    private final String secretKey;
    private  Duration expiresIn;

    public TokenService(@Value("${security.token.secret.candidate}") String secretKey) {
        this.secretKey = secretKey;
    }

    public Duration getExpiresIin(){
        return expiresIn;
    }

     public String generateToken(UUID userId, UserRole role, Duration expiresIn) {
        this.expiresIn = expiresIn;
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresAt = Instant.now().plus(expiresIn);

        return JWT.create()
                .withIssuer("gestao de projetos")
                .withSubject(userId.toString())
                .withClaim("roles", Arrays.asList(role.toString()))
                .withExpiresAt(expiresAt)
                .sign(algorithm);
    }
}

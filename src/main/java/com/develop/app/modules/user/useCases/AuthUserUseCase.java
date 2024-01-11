package com.develop.app.modules.user.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.develop.app.modules.user.dto.AuthUserResponseDTO;
import com.develop.app.modules.user.exceptions.UserEmailNotFoundException;
import com.develop.app.modules.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Value;
import java.time.Duration;


import java.time.Instant;
import java.util.Arrays;

import javax.security.sasl.AuthenticationException;



@Service
public class AuthUserUseCase {
  @Value("${security.token.secret.candidate}")
  private String secretKey;


  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthUserResponseDTO execute() throws AuthenticationException {
    var user = this.userRepository.findByEmail("").orElseThrow(()->{
      throw new UserEmailNotFoundException();
    });

    var passwordMatches = this.passwordEncoder
    .matches("", user.getPassword());

    if (!passwordMatches) {
      throw new AuthenticationException();
    }

    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    var expiresIn = Instant.now().plus(Duration.ofMinutes(10));
    var token = JWT.create()
        .withIssuer("javagas")
        .withSubject(user.getId().toString())
        .withClaim("roles", Arrays.asList("USER"))
        .withExpiresAt(expiresIn)
        .sign(algorithm);

    var AuthUserResponse = AuthUserResponseDTO.builder()
        .access_token(token)
        .expires_in(null)
        .build();

    return AuthUserResponse;
  }


}



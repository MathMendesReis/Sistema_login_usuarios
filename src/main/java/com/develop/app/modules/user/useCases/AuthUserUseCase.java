package com.develop.app.modules.user.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.develop.app.modules.user.dto.AuthUserRequestDTO;
import com.develop.app.modules.user.dto.AuthUserResponseDTO;
import com.develop.app.modules.user.exceptions.UserNotFoundException;
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

  public AuthUserResponseDTO execute(AuthUserRequestDTO authUserRequestDTO) throws AuthenticationException {
    var user = this.userRepository.findByEmail(authUserRequestDTO.email()).orElseThrow(()->{
      throw new UserNotFoundException();
    });

    // Verificar senhas
    var passwordMatches = this.passwordEncoder
    .matches(authUserRequestDTO.password(), user.getPassword());

    if (passwordMatches == false) {
      throw new AuthenticationException("email/password incorrect");
    }

    
    // Gerar token
    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    var expiresIn = Instant.now().plus(Duration.ofHours(2));
    
    var token = JWT.create()
        .withIssuer("javagas")
        .withSubject(user.getId().toString())
        .withClaim("roles", Arrays.asList(user.getRole().toString()))
        .withExpiresAt(expiresIn)
        .sign(algorithm);

    var AuthUserResponse = AuthUserResponseDTO.builder()
        .access_token(token)
        .expires_in(expiresIn.toEpochMilli())
        .build();

    return AuthUserResponse;
  }


}



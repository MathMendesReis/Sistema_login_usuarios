package com.develop.app.modules.auth.service;


import java.time.Duration;
import java.time.Instant;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.develop.app.modules.auth.dto.AuthRequestDTO;
import com.develop.app.modules.auth.dto.AuthUserResponseDTO;
import com.develop.app.modules.providers.TokenService;
import com.develop.app.modules.users.UserRepository;
import com.develop.app.modules.users.exceptions.UserNotFoundException;

@Service
public class AuthUseCase {
    
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;


    public AuthUserResponseDTO execute(AuthRequestDTO authRequestDTO) throws AuthenticationException{
        var user = this.userRepository.findByEmail(authRequestDTO.email()).orElseThrow(()->{
            throw new UserNotFoundException();
          });
        
        var passwordMatches = this.passwordEncoder
        .matches(authRequestDTO.password(), user.getPassword());
        if (passwordMatches == false) {
            throw new AuthenticationException("email/password incorrect");
        }

        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var token = tokenService.generateToken(user.getId(), user.getRole(), Duration.ofHours(2));
        var response = AuthUserResponseDTO.builder()
         .access_token(token)
         .expires_in(expiresIn.toEpochMilli())
         .build();
        return response;
    }
}

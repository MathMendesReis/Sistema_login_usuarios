package com.develop.app.modules.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.app.modules.user.dto.AuthUserRequestDTO;
import com.develop.app.modules.user.useCases.AuthUserUseCase;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/user")
public class AuthUserController {

  @Autowired
  private AuthUserUseCase authUserUseCase;

  @PostMapping("/login")
  public ResponseEntity<Object> auth(@RequestBody AuthUserRequestDTO authUserRequestDTO){
    try {
      var token = this.authUserUseCase.execute();
      return ResponseEntity.ok().body(token);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }
}

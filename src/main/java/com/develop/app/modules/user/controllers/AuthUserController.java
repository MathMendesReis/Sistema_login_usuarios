package com.develop.app.modules.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.app.modules.user.dto.AuthUserRequestDTO;
import com.develop.app.modules.user.dto.CreateUserRequestDTO;
import com.develop.app.modules.user.useCases.AuthUserUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/auth")
@Tag(name = "Autentificação", description = "Login")
public class AuthUserController {

  @Autowired
  private AuthUserUseCase authUserUseCase;

  @PostMapping("/login")
  @Operation(summary = "Login", description = "Essa função é responsável por logar um usuario")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = AuthUserRequestDTO.class))
      }),
      @ApiResponse(responseCode = "400", description = "Email/password incorrect.")
  })
  public ResponseEntity<Object> auth(@RequestBody AuthUserRequestDTO authUserRequestDTO){
    try {
      var token = this.authUserUseCase.execute(authUserRequestDTO);
      return ResponseEntity.ok().body(token);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }
}

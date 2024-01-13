package com.develop.app.modules.user.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.app.modules.user.dto.CreateUserRequestDTO;
import com.develop.app.modules.user.useCases.CreatedUserUseCase;
import com.develop.app.modules.user.useCases.DeleteUserUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/user")
@Tag(name = "Usuarios", description = "Login")

public class UserController {

  @Autowired
  private CreatedUserUseCase createdUserUseCase;

  @Autowired
  private DeleteUserUseCase deleteUserUseCase;


  @PostMapping("/register")
   @Operation(summary = "Cadastro de usuario", description = "Essa função é responsável por cadastrar um usuario")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = CreateUserRequestDTO.class))
      }),
      @ApiResponse(responseCode = "400", description = "Usuário já existe")
  })
  public ResponseEntity<Object> create(@RequestBody CreateUserRequestDTO createUserRequestDTO){
    try {
      var result = this.createdUserUseCase.create(createUserRequestDTO);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @DeleteMapping("/delete")
  @Operation(summary = "Deletar usuario", description = "Essa funçãe e responsavel por deletar um usuario")
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> deleteUser(HttpServletRequest request){
    var idUser = request.getAttribute("user_id");

    try {
      var result = this.deleteUserUseCase.deleteUser(UUID.fromString(idUser.toString()));

      return ResponseEntity.ok().body(result);
      
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
      
    }
  }
}

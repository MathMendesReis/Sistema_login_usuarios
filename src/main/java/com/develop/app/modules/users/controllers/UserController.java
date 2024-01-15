package com.develop.app.modules.users.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.app.modules.users.dto.CreateUserRequestDTO;
import com.develop.app.modules.users.dto.UpdateRequestDTO;
import com.develop.app.modules.users.exceptions.InvalidRoleException;
import com.develop.app.modules.users.service.CreateUserUseCase;
import com.develop.app.modules.users.service.DeleteUserUseCase;
import com.develop.app.modules.users.service.GetAllUsers;
import com.develop.app.modules.users.service.UpdateUserUseCases;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Tag(name = "User")
public class UserController {
    
    @Autowired
    private CreateUserUseCase createUserUseCase;

    @Autowired
    private DeleteUserUseCase deleteUserUseCase;

    @Autowired
    private GetAllUsers getAllUsers;

    @Autowired
    private UpdateUserUseCases updateUserUseCases;

    @PostMapping("/register")
    @Operation(summary = "Cadastro de usuario", description = "Essa função é responsável por cadastrar um usuario")
    @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(schema = @Schema(implementation = CreateUserRequestDTO.class))
      }),
      @ApiResponse(
        responseCode = "400",
        content = @Content(schema = @Schema(implementation = InvalidRoleException.class))
    )
    })
    public ResponseEntity<Object> create(@Valid @RequestBody CreateUserRequestDTO createUserRequestDTO){
        try {
             var result = this.createUserUseCase.execute(createUserRequestDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Exclusão de usuario", description = "Essa função é responsável por excluir um usuario")
    @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
      }),
    })
    @SecurityRequirement(name = "jwt_auth")
      public ResponseEntity<Object> delete(HttpServletRequest request){
        var token = request.getAttribute("user_id");
        try {
            var result = this.deleteUserUseCase.execute(UUID.fromString(token.toString()));
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/update")
    @SecurityRequirement(name = "jwt_auth")
      public ResponseEntity<Object> update(@Valid @RequestBody UpdateRequestDTO updateRequestDTO,HttpServletRequest request ){
        var token = request.getAttribute("user_id");
        try {
            var result = this.updateUserUseCases.execute(UUID.fromString(token.toString()), updateRequestDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers(){
        try {
            var result = this.getAllUsers.execute();
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    
}

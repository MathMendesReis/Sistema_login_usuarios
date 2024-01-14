package com.develop.app.modules.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.app.modules.users.dto.CreateUserRequestDTO;
import com.develop.app.modules.users.exceptions.InvalidRoleException;
import com.develop.app.modules.users.service.CreateUserUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@Tag(name = "user")
public class UserController {
    
    @Autowired
    private CreateUserUseCase createUserUseCase;

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
    public ResponseEntity<Object> create(@RequestBody CreateUserRequestDTO createUserRequestDTO){
        try {
             var result = this.createUserUseCase.execute(createUserRequestDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    
}

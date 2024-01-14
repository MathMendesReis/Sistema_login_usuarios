package com.develop.app.docs;

import org.springframework.http.ResponseEntity;

import com.develop.app.modules.users.dto.CreateUserRequestDTO;
import com.develop.app.modules.users.exceptions.InvalidRoleException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
public class exampleControlller {
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
     public ResponseEntity<Object> create(){
        try {
            return ResponseEntity.ok().body("");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

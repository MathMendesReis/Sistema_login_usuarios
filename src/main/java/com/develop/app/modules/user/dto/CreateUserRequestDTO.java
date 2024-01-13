package com.develop.app.modules.user.dto;


import com.develop.app.modules.user.entity.UserRole;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

public record CreateUserRequestDTO(

  @Schema(example = "maria@gmail.com", requiredMode = RequiredMode.REQUIRED)
   String email,
  @Schema(example = "Matheus", requiredMode = RequiredMode.REQUIRED)
  String name,
  @Schema(example = "secret1234", requiredMode = RequiredMode.REQUIRED)
  String password,
  @Schema(example = "ADMIN", required = true)
   UserRole role
) {
  
}



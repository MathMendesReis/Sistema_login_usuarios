package com.develop.app.modules.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

public record AuthUserRequestDTO(
    @Schema(example = "maria@gmail.com", requiredMode = RequiredMode.REQUIRED)
    String email, 
    @Schema(example = "secret1234", requiredMode = RequiredMode.REQUIRED)
    String password) {
  
}

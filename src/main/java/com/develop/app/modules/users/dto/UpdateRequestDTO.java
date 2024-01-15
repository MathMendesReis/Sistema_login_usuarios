package com.develop.app.modules.users.dto;


import com.develop.app.modules.users.types.UserRole;
import com.develop.app.modules.users.types.UserStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

public record UpdateRequestDTO(
    @Schema(example = "maria do bairro", requiredMode = RequiredMode.NOT_REQUIRED)
    String name,
    @Schema(example = "mariadobairro@example.com", requiredMode = RequiredMode.NOT_REQUIRED)
    String email,
    @Schema(example = "mariadobairro1234",requiredMode = RequiredMode.REQUIRED)
    String password,
    @Schema (example = "ADMIM", requiredMode = RequiredMode.NOT_REQUIRED)
    UserRole role,
    @Schema (example = "ACTIVE", requiredMode = RequiredMode.NOT_REQUIRED)
    UserStatus status
) {

  
}

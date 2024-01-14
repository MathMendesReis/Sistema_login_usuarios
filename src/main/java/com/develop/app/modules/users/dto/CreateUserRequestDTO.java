package com.develop.app.modules.users.dto;

import com.develop.app.modules.users.types.UserRole;
import com.develop.app.modules.users.types.UserStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

public record CreateUserRequestDTO(
    @Schema(example = "maria do bairro", requiredMode = RequiredMode.REQUIRED)
    String name,
    @Schema(example = "mariadobairro@example.com", requiredMode = RequiredMode.REQUIRED)
    String email,
    @Schema(example = "mariadobairro1234",requiredMode = RequiredMode.REQUIRED)
    String password,
    UserRole role
    // UserStatus status
) {
    
}

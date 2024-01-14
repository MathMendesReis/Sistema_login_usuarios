package com.develop.app.modules.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

public record AuthRequestDTO(
    @Schema(example = "mariadobairro@example.com", requiredMode = RequiredMode.REQUIRED)
    String email,
    @Schema(example = "mariadobairro1234",requiredMode = RequiredMode.REQUIRED)
    String password
) {
    
}

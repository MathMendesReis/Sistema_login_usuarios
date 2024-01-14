package com.develop.app.modules.users.types;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

@Schema (example = "ADMIM", requiredMode = RequiredMode.REQUIRED)
public enum UserRole {
    ADMIM("admin"),
    USER("user");

    private String role;

    UserRole(String role){
      this.role = role;
    }
  
    public String getRole(){
      return role;
  }
}

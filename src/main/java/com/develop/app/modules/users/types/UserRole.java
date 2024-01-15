package com.develop.app.modules.users.types;

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

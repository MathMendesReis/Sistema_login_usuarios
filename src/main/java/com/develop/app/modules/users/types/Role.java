package com.develop.app.modules.users.types;

public enum Role {
    ADMIM("admin"),
    USER("user");

    private String role;

    Role(String role){
      this.role = role;
    }
  
    public String getRole(){
      return role;
  }
}

package com.develop.app.modules.users.types;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(example = "active")

public enum UserStatus {
    ACTIVE("active");

    private String status;

    UserStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }
}

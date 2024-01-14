package com.develop.app.modules.users.types;

public enum Status {
    ACTIVE("active");

    private String status;

    Status(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }
}

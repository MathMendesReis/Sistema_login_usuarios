package com.develop.app.modules.users.exceptions;

public class InvalidRoleException extends RuntimeException {
    public InvalidRoleException(){
        super("user role invalid");
    }
}

package com.develop.app.modules.users.exceptions;

public class UserFoundException extends RuntimeException {
    public UserFoundException(){
        super("User already exists.");
    }
}

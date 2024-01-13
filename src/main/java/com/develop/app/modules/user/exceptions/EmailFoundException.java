package com.develop.app.modules.user.exceptions;

public class EmailFoundException extends RuntimeException {
    public EmailFoundException(){
        super("Email already registerd");
    }
}

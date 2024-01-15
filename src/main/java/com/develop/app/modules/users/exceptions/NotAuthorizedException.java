package com.develop.app.modules.users.exceptions;

public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException(){
        super("not authorized");
    }
}

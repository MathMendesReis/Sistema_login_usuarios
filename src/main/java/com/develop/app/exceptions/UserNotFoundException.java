package com.develop.app.exceptions;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(){
    super("User not found");
}
}

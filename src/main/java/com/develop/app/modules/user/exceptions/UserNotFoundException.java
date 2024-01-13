package com.develop.app.modules.user.exceptions;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(){
    super("Not found");
  }
}

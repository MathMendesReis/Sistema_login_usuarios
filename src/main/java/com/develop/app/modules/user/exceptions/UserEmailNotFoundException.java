package com.develop.app.modules.user.exceptions;

public class UserEmailNotFoundException extends RuntimeException {
  public UserEmailNotFoundException(){
    super("Not found");
  }
}

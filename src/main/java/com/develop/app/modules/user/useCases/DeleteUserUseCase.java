package com.develop.app.modules.user.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develop.app.exceptions.UserNotFoundException;
import com.develop.app.modules.user.repository.UserRepository;

/**
 * DeleteUserUseCase
 */
@Service
public class DeleteUserUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FindUserById findUserById;

    public String deleteUser(UUID uuid){
      var user = this.findUserById.execute(UUID.fromString(uuid.toString()));
      if (user == null) {
        throw new UserNotFoundException();
    };
    this.userRepository.delete(user);
    return "Delete user sucessfull";
    }
}
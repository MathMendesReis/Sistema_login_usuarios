package com.develop.app.modules.user.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.develop.app.modules.user.dto.CreateUserRequestDTO;
import com.develop.app.modules.user.entity.UsersEntity;
import com.develop.app.modules.user.exceptions.EmailFoundException;
import com.develop.app.modules.user.repository.UserRepository;

@Service
public class CreatedUserUseCase {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;


  public UsersEntity create(CreateUserRequestDTO createUserRequestDTO){

    this.userRepository.findByEmail(createUserRequestDTO.email())
    .ifPresent(user -> {
      throw new EmailFoundException();
    });

    UsersEntity newUser = UsersEntity.builder()
        .email(createUserRequestDTO.email())
        .name(createUserRequestDTO.name())
        .password(createUserRequestDTO.password())
        .role(createUserRequestDTO.role())
        .build();

    var password = passwordEncoder.encode(newUser.getPassword());
    newUser.setPassword(password);
    var user =this.userRepository.save(newUser);
    return user;
  }
  
}

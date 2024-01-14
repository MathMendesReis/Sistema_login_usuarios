package com.develop.app.modules.users.service;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.develop.app.modules.users.UserEntity;
import com.develop.app.modules.users.UserRepository;
import com.develop.app.modules.users.dto.CreateUserRequestDTO;
import com.develop.app.modules.users.exceptions.UserFoundException;
import com.develop.app.modules.users.types.UserStatus;

@Service

public class CreateUserUseCase {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public UserEntity execute(CreateUserRequestDTO createUserRequestDTO) throws BadRequestException{
        this.userRepository.findByEmail(createUserRequestDTO.email())
            .ifPresent(user -> {
        throw new UserFoundException();
         });

        UserEntity user = UserEntity.builder()
        .name(createUserRequestDTO.name())
        .email(createUserRequestDTO.email())
        .password(createUserRequestDTO.password())
        .role(createUserRequestDTO.role())
        .status(UserStatus.ACTIVE)
        .build();


        var password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        this.userRepository.save(user);
        return user;
    }
}

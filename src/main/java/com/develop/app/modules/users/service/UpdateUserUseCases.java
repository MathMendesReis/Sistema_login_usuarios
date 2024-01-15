package com.develop.app.modules.users.service;

import java.util.UUID;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.develop.app.modules.users.UserEntity;
import com.develop.app.modules.users.UserRepository;
import com.develop.app.modules.users.dto.UpdateRequestDTO;
import com.develop.app.modules.users.exceptions.UserNotFoundException;

@Service
public class UpdateUserUseCases {

    @Autowired
    private UserRepository repository;

    public UserEntity execute(UUID uuid, UpdateRequestDTO updateRequestDTO) throws BadRequestException {
        if (uuid == null) {
            throw new BadRequestException();
        }
        UserEntity user = repository.findById(uuid).orElseThrow(() -> new UserNotFoundException());
        if(!updateRequestDTO.name().isEmpty())((UserEntity) user).setName(updateRequestDTO.name());
        if(!updateRequestDTO.email().isEmpty()) ((UserEntity) user).setEmail(updateRequestDTO.email());
        if(updateRequestDTO.role() != null ) ((UserEntity) user).setRole(updateRequestDTO.role());
        if(updateRequestDTO.status() != null) ((UserEntity) user).setStatus(updateRequestDTO.status());

        if (user == null) {
            throw new BadRequestException();
        }
        return repository.save(user);
    }
}
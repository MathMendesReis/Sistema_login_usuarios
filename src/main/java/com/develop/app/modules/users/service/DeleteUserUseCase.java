package com.develop.app.modules.users.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develop.app.modules.users.UserRepository;
import com.develop.app.modules.users.exceptions.UserNotFoundException;

@Service
public class DeleteUserUseCase {

    @Autowired
    private UserRepository userRepository;

    public String execute(UUID uuid) throws Exception{
        if (uuid == null) {
            throw new Exception("Token invalido");
        }

        this.userRepository.findById(uuid).orElseThrow(()->{
            throw new UserNotFoundException();
        });

        this.userRepository.deleteById(uuid);

        return "usuario excluido";
    }
}

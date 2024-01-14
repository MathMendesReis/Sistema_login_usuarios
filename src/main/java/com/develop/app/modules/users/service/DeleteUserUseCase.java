package com.develop.app.modules.users.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develop.app.modules.users.UserRepository;

@Service
public class DeleteUserUseCase {

    @Autowired
    private UserRepository userRepository;

    public String execute(UUID uuid){
        this.userRepository.deleteById(uuid);

        return "usuario excluido";
    }
}

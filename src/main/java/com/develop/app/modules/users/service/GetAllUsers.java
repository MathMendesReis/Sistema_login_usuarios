package com.develop.app.modules.users.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develop.app.modules.users.UserEntity;
import com.develop.app.modules.users.UserRepository;

@Service
public class GetAllUsers {
    
    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> execute(){
        return this.userRepository.findAll();
    }
}

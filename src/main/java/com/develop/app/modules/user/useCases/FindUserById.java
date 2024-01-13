package com.develop.app.modules.user.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develop.app.exceptions.UserNotFoundException;
import com.develop.app.modules.user.entity.UsersEntity;
import com.develop.app.modules.user.repository.UserRepository;

@Service
public class FindUserById {
    
    @Autowired
    private UserRepository userRepository;

    public UsersEntity execute(UUID uuid){
        var user = this.userRepository.findById(uuid).orElseThrow(()->{
            throw new UserNotFoundException();
        });
        return user;
    }
}

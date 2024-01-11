package com.develop.app.modules.user.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.develop.app.modules.user.entity.UsersEntity;


public interface UserRepository extends JpaRepository<UsersEntity, UUID>{

  Optional<UsersEntity> findByEmail(String email);
  
}

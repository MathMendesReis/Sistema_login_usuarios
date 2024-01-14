package com.develop.app.modules.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.app.modules.auth.dto.AuthRequestDTO;
import com.develop.app.modules.auth.service.AuthUseCase;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RequestMapping("/auth")
@RestController
@Tag(name = "Auth")
public class AuthController {
    @Autowired
    private AuthUseCase authUseCase;

    @PostMapping("/login")
    public ResponseEntity<Object> auth(@Valid @RequestBody AuthRequestDTO authRequestDTO){
        try {
            var result = this.authUseCase.execute(authRequestDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

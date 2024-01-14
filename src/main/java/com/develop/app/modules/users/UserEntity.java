package com.develop.app.modules.users;

import java.util.UUID;

import javax.management.relation.Role;

import org.hibernate.validator.constraints.Length;

import com.develop.app.modules.users.types.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_tb")
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "This field cannot be blank")
    @Length(min = 1, max = 255, message = "The name must be between 1 and 255 characters")
    @Column(unique=true)
    private String name;

    @Email(message = "Invalid E-mail")
    @Column(unique=true)
    private String email;

    @Length(min = 10, max = 100, message = "The password must be between 10 and 100 characters")
    private String password;

    @NotBlank(message = "This field cannot be blank")
    private Role role;

    @NotBlank(message = "This field cannot be blank")
    private Status status;
}

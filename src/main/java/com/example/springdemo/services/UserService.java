package com.example.springdemo.services;

import com.example.springdemo.entities.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Users getUsersByEmail(String email);

    Users createUser(Users user);

}

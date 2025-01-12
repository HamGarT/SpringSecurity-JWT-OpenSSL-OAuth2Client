package com.hamgar.JwtOpenSslAndOAuthClient.service;

import com.hamgar.JwtOpenSslAndOAuthClient.model.User;
import com.hamgar.JwtOpenSslAndOAuthClient.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }
}

package com.hamgar.JwtOpenSslAndOAuthClient.controller;

import com.hamgar.JwtOpenSslAndOAuthClient.model.User;
import com.hamgar.JwtOpenSslAndOAuthClient.model.dto.ResponseDTO;
import com.hamgar.JwtOpenSslAndOAuthClient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> findAll(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }
}

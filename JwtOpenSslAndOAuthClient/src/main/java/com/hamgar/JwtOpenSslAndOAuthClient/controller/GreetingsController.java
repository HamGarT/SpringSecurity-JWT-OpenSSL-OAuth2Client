package com.hamgar.JwtOpenSslAndOAuthClient.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class GreetingsController {
    @GetMapping
    public String sayHello(){
        return "hello";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/hello-admin")
    public String sayHelloAdmin(){
        return "hello admin";
    }
}

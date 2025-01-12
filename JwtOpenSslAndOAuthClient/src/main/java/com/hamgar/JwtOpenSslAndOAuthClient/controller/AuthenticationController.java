package com.hamgar.JwtOpenSslAndOAuthClient.controller;

import com.hamgar.JwtOpenSslAndOAuthClient.auth.AuthenticationService;
import com.hamgar.JwtOpenSslAndOAuthClient.model.dto.LoginDTO;
import com.hamgar.JwtOpenSslAndOAuthClient.model.dto.RegisterDTO;
import com.hamgar.JwtOpenSslAndOAuthClient.model.dto.ResponseDTO;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterDTO user) throws Exception{
        return new ResponseEntity<>(authenticationService.register(user), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<HashMap<String, String>> login(@RequestBody LoginDTO loginRequest) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, JOSEException {
        HashMap<String, String> login = authenticationService.login(loginRequest);
        if(login.containsKey("jwt")){
            return new ResponseEntity<>(authenticationService.login(loginRequest), HttpStatus.ACCEPTED);
        } else{
            return new ResponseEntity<>(authenticationService.login(loginRequest), HttpStatus.UNAUTHORIZED);
        }
    }
}

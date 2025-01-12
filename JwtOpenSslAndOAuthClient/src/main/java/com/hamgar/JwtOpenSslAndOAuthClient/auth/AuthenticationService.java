package com.hamgar.JwtOpenSslAndOAuthClient.auth;

import com.hamgar.JwtOpenSslAndOAuthClient.config.JwtService;
import com.hamgar.JwtOpenSslAndOAuthClient.model.User;
import com.hamgar.JwtOpenSslAndOAuthClient.model.dto.LoginDTO;
import com.hamgar.JwtOpenSslAndOAuthClient.model.dto.RegisterDTO;
import com.hamgar.JwtOpenSslAndOAuthClient.model.dto.ResponseDTO;
import com.hamgar.JwtOpenSslAndOAuthClient.repository.UserRepository;
import com.nimbusds.jose.JOSEException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public HashMap<String, String> login(LoginDTO loginRequest) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, JOSEException {
        HashMap<String, String> jwt = new HashMap<>();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new EntityNotFoundException("User not found"));
        jwt.put("jwt", jwtService.generateToken(user));

        /*if(verifyPassword(loginRequest.getPassword(), user.getPassword())){
            jwt.put("jwt", jwtService.generateToken(user));
        }else{
            jwt.put("error", "Authentication failed");
        }*/
        return jwt;
    }

    public ResponseDTO register(RegisterDTO registerRequest){
        ResponseDTO response = new ResponseDTO();
        //List<User> getAllUsers = userRepository.findAll();
        /*for (User repeatFields : getAllUsers) {
            if (repeatFields != null) {
                response.setMessage("User already exists!");
                return response;
            }
        }*/
        User user = User.builder()
                .name(registerRequest.getName())
                .lastname(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .publicId(UUID.randomUUID())
                .build();
        userRepository.save(user);
        response.setMessage("User created successfully!");
        return response;
    }

    private boolean verifyPassword(String enteredPassword, String storedPassword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, storedPassword);
    }

}

package com.hamgar.JwtOpenSslAndOAuthClient.model.dto;

import com.hamgar.JwtOpenSslAndOAuthClient.model.enums.Role;
import lombok.Data;

@Data
public class RegisterDTO {
    private String name;
    private String lastname;
    private String email;
    private String password;
    private Role role;
}

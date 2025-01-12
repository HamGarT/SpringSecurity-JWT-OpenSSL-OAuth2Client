package com.hamgar.JwtOpenSslAndOAuthClient.model.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String email;
    private String password;
}

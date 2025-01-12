package com.hamgar.JwtOpenSslAndOAuthClient.model.dto;

import lombok.Data;

@Data
public class ResponseDTO {
    private int numOfErrors;
    private String message;
}

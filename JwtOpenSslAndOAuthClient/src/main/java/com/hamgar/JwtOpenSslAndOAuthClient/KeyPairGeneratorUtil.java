package com.hamgar.JwtOpenSslAndOAuthClient;

import com.hamgar.JwtOpenSslAndOAuthClient.config.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class KeyPairGeneratorUtil {
    public Resource publicKeyResource;
     public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
         // Create a DefaultResourceLoader instance
         DefaultResourceLoader resourceLoader = new DefaultResourceLoader();

         // Load the resource using the classpath
         Resource resource = resourceLoader.getResource("classpath:jwtkeys/private_key.pem");
         JwtService jwtService = new JwtService();

     }

}

package com.hamgar.JwtOpenSslAndOAuthClient.oauth;

import com.hamgar.JwtOpenSslAndOAuthClient.model.User;
import com.hamgar.JwtOpenSslAndOAuthClient.model.enums.Role;
import com.hamgar.JwtOpenSslAndOAuthClient.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuthUserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private UserRepository userRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("hola");
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email");
        //String registrationId = userRequest.getClientRegistration().getRegistrationId();
        //String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        User user = userRepository.findByEmail(email).orElseGet(() -> createNewUser(oAuth2User));
        System.out.println(user.getAuthorities());
        return new DefaultOAuth2User(user.getAuthorities(),
                oAuth2User.getAttributes(),
                "email"

        );
    }

    private User createNewUser(OAuth2User oAuth2User ){
        User newUser = User.builder()
                .email(oAuth2User.getAttribute("email"))
                .name(oAuth2User.getAttribute("name"))
                .role(Role.ROLE_USER)
                //.photo(oAuth2User.getAttribute("photo"))
                .build();
        return userRepository.save(newUser);
    }
}

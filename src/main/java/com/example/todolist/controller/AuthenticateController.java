package com.example.todolist.controller;

import com.example.todolist.jwt.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/authenticated")
public class AuthenticateController {

    @Autowired
    private JwtService jwtService;

    @GetMapping
    public ResponseEntity<String> authenticated(OAuth2AuthenticationToken authentication){
        if(authentication != null){
            log.info("id {}, name {}",authentication.getAuthorizedClientRegistrationId(),
                    authentication.getName());
            return new ResponseEntity<>(jwtService.generateToken(), HttpStatus.OK);
        }
        return new ResponseEntity<>("no oauth2 login found", HttpStatus.OK);
    }
}

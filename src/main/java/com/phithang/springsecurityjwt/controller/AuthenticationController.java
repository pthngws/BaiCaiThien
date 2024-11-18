package com.phithang.springsecurityjwt.controller;

import com.phithang.springsecurityjwt.entity.Users;
import com.phithang.springsecurityjwt.model.LoginResponse;
import com.phithang.springsecurityjwt.model.LoginUserModel;
import com.phithang.springsecurityjwt.model.RegisterUserModel;
import com.phithang.springsecurityjwt.service.AuthenticationService;
import com.phithang.springsecurityjwt.service.JwtService;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @Transactional
    @PostMapping("/signup")
    public ResponseEntity<Users> register(@RequestBody RegisterUserModel registerUser) {
        Users registeredUser = authenticationService.signup(registerUser);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserModel loginUser) {
        Users authenticatedUser = authenticationService.authenticate(loginUser);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpires(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}


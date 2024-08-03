package com.example.emsbackend.controller.auths;

import com.example.emsbackend.dto.auths.*;
import com.example.emsbackend.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request) {
        System.out.println("hello");
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        try {
            return ResponseEntity.ok(authenticationService.authenticate(request));

        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }


    @PostMapping("/check-email-validity")
    public ResponseEntity<ValidationResponse> checkEmailValidity(@RequestBody ValidationRequest request) {
        ValidationResponse validationResponse = authenticationService.checkEmail(request);

        return ResponseEntity.status(200).body(validationResponse);
    }
}

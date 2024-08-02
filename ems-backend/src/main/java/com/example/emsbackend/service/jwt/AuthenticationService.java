package com.example.emsbackend.service.jwt;


import com.example.emsbackend.dto.auths.*;


public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}

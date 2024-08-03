package com.example.emsbackend.service.auth;


import com.example.emsbackend.dto.auths.*;


public interface AuthenticationService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticationResponse logout(AuthenticationRequest request);

    ValidationResponse checkEmail(ValidationRequest request);

}

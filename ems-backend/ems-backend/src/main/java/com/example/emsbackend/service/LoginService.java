package com.example.emsbackend.service;

import com.example.emsbackend.api.dto.UserEntityDTO;


public interface LoginService {

    UserEntityDTO findUserByEmailAndPasswordHash(String email, String passwordHash);

}

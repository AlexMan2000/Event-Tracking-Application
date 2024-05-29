package com.example.emsbackend.service;

import com.example.emsbackend.dto.primary.UserEntityDTO;


public interface LoginService {

    UserEntityDTO findUserByEmailAndPasswordHash(String email, String passwordHash);

}

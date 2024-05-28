package com.example.emsbackend.service;

import com.example.emsbackend.dto.UserEntityDTO;


public interface LoginService {

    UserEntityDTO findUserByEmailAndPasswordHash(String email, String passwordHash);

}

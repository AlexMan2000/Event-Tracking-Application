package com.example.emsbackend.service.users;

import com.example.emsbackend.dto.users.UserEntityDTO;


public interface LoginService {

    UserEntityDTO findUserByEmailAndPasswordHash(String email, String passwordHash);

}

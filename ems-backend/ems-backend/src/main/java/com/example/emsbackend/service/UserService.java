package com.example.emsbackend.service;

import com.example.emsbackend.api.dto.RoleEntityDTO;
import com.example.emsbackend.api.dto.UserEntityDTO;

import java.util.List;

public interface UserService {

    UserEntityDTO getUserDTOById(Long id);

    RoleEntityDTO getUserRoleById(Long id);

    UserEntityDTO createOrUpdateUser(UserEntityDTO userEntityDTO);

    UserEntityDTO updateUserById(UserEntityDTO userEntityDTO, Long id);

    void deleteUserById(Long id);

}

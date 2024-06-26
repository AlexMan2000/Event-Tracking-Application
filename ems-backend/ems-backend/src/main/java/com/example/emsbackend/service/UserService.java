package com.example.emsbackend.service;

import com.example.emsbackend.dto.primary.RoleEntityDTO;
import com.example.emsbackend.dto.primary.UserEntityDTO;
import com.example.emsbackend.service.utils.UserEntitySearchCriteria;

import java.util.List;

public interface UserService {


    List<UserEntityDTO> getUserDTOByFilter(UserEntitySearchCriteria userEntitySearchCriteria);

    UserEntityDTO getUserDTOById(Long id);

    UserEntityDTO getUserDTOByEmail(String email);

    RoleEntityDTO getUserRoleById(Long id);

    UserEntityDTO createOrUpdateUser(UserEntityDTO userEntityDTO);

    UserEntityDTO updateUserByEmail(UserEntityDTO userEntityDTO, String email);

    UserEntityDTO updateUserById(UserEntityDTO userEntityDTO, Long id);



    void deleteUserById(Long id);

}

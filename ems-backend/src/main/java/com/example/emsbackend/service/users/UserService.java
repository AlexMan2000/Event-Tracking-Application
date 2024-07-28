package com.example.emsbackend.service.users;

import com.example.emsbackend.dto.users.RoleEntityDTO;
import com.example.emsbackend.dto.users.UserEntityDTO;
import com.example.emsbackend.criteria_utils.searching.UserEntitySearchCriteria;

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

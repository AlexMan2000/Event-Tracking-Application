package com.example.emsbackend.service.impl;

import com.example.emsbackend.api.dto.RoleEntityDTO;
import com.example.emsbackend.api.dto.UserEntityDTO;
import com.example.emsbackend.persistence.entity.UserEntity;
import com.example.emsbackend.persistence.repository.UserEntityRepository;
import com.example.emsbackend.service.RoleService;
import com.example.emsbackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserEntityRepository userEntityRepository;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper
            , UserEntityRepository userEntityRepository
            , RoleService roleService) {
        this.modelMapper = modelMapper;
        this.userEntityRepository = userEntityRepository;
        this.roleService = roleService;
    }

    @Override
    public UserEntityDTO getUserDTOById(Long id) {
        return entityToDto(userEntityRepository.findUserEntityById(id));
    }

    @Override
    public RoleEntityDTO getUserRoleById(Long id) {
        Long roleId = userEntityRepository.findUserEntityById(id).getRoleId();
        if (roleId == null) {
            throw new RuntimeException("Resource not found");
        }
        return roleService.getRoleEntityById(roleId);
    }

    @Override
    public UserEntityDTO createOrUpdateUser(UserEntityDTO userEntityDTO) {
        return null;
    }

    @Override
    public UserEntityDTO updateUserById(UserEntityDTO userEntityDTO, Long id) {
        return null;
    }

    @Override
    public void deleteUserById(Long id) {

    }


    private UserEntityDTO entityToDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserEntityDTO.class);
    }


    private UserEntity dtoToEntity(UserEntityDTO userEntityDTO) {
        return modelMapper.map(userEntityDTO, UserEntity.class);
    }


}

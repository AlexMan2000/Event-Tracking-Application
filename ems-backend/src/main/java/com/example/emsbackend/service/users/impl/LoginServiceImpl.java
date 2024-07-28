package com.example.emsbackend.service.users.impl;

import com.example.emsbackend.dto.users.UserEntityDTO;
import com.example.emsbackend.entity.users.UserEntity;
import com.example.emsbackend.repository.users.UserEntityRepository;
import com.example.emsbackend.service.users.LoginService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService {


    private final ModelMapper modelMapper;
    private final UserEntityRepository userEntityRepository;

    @Autowired
    public LoginServiceImpl(ModelMapper modelMapper, UserEntityRepository userEntityRepository) {
        this.modelMapper = modelMapper;
        this.userEntityRepository = userEntityRepository;
    }
    @Override
    public UserEntityDTO findUserByEmailAndPasswordHash(String email, String passwordHash) {
        return entityToDto(userEntityRepository.findByEmailAndPassword(email, passwordHash));
    }


    private UserEntityDTO entityToDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return modelMapper.map(userEntity, UserEntityDTO.class);
    }


    private UserEntity dtoToEntity(UserEntityDTO userEntityDTO) {
        if (userEntityDTO == null) {
            return null;
        }
        return modelMapper.map(userEntityDTO, UserEntity.class);
    }
}

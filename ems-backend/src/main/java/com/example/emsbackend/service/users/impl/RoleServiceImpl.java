package com.example.emsbackend.service.users.impl;

import com.example.emsbackend.dto.users.RoleEntityDTO;
import com.example.emsbackend.entity.users.RoleEntity;
import com.example.emsbackend.repository.users.RoleEntityRepository;
import com.example.emsbackend.service.users.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {

    private final ModelMapper modelMapper;
    private final RoleEntityRepository roleEntityRepository;

    @Autowired
    public RoleServiceImpl(ModelMapper modelMapper, RoleEntityRepository roleEntityRepository) {
        this.modelMapper = modelMapper;
        this.roleEntityRepository = roleEntityRepository;
    }

    @Override
    public RoleEntityDTO getRoleEntityById(Long id) {
        RoleEntity roleEntity = roleEntityRepository.findRoleEntitiesById(id);
        return roleEntity != null ? entityToDto(roleEntity) : null;
    }



    private RoleEntityDTO entityToDto(RoleEntity roleEntity) {
        return modelMapper.map(roleEntity, RoleEntityDTO.class);
    }


    private RoleEntity dtoToEntity(RoleEntityDTO roleEntityDTO) {
        return modelMapper.map(roleEntityDTO, RoleEntity.class);
    }
}

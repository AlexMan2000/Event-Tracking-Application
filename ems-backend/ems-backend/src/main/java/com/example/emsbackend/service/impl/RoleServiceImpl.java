package com.example.emsbackend.service.impl;

import com.example.emsbackend.api.dto.RoleEntityDTO;
import com.example.emsbackend.persistence.entity.RoleEntity;
import com.example.emsbackend.persistence.repository.RoleEntityRepository;
import com.example.emsbackend.service.RoleService;
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

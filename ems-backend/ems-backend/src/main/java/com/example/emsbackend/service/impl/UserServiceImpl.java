package com.example.emsbackend.service.impl;

import com.example.emsbackend.dto.primary.RoleEntityDTO;
import com.example.emsbackend.dto.primary.UserEntityDTO;
import com.example.emsbackend.service.utils.UserEntitySearchCriteria;
import com.example.emsbackend.entity.primary.UserEntity;
import com.example.emsbackend.repository.primary.UserEntityCriteriaRepository;
import com.example.emsbackend.repository.primary.UserEntityRepository;
import com.example.emsbackend.service.RoleService;
import com.example.emsbackend.service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserEntityRepository userEntityRepository;
    private final UserEntityCriteriaRepository userEntityCriteriaRepository;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper
            , UserEntityRepository userEntityRepository
            , RoleService roleService
            , UserEntityCriteriaRepository userEntityCriteriaRepository) {
        this.modelMapper = modelMapper;
        this.userEntityRepository = userEntityRepository;
        this.roleService = roleService;
        this.userEntityCriteriaRepository = userEntityCriteriaRepository;
    }

    @Override
    public List<UserEntityDTO> getUserDTOByFilter(UserEntitySearchCriteria userEntitySearchCriteria) {
        return this.userEntityCriteriaRepository
                .findAllWithFilters(userEntitySearchCriteria)
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserEntityDTO getUserDTOById(Long id) {
        return entityToDto(userEntityRepository.findUserEntityById(id));
    }

    @Override
    public UserEntityDTO getUserDTOByEmail(String email) { return entityToDto(userEntityRepository.findUserEntityByEmail(email));}

    @Override
    public RoleEntityDTO getUserRoleById(Long id) {
        Long roleId = userEntityRepository.findUserEntityById(id).getRoleId();
        if (roleId == null) {
            throw new RuntimeException("Resource not found");
        }
        return roleService.getRoleEntityById(roleId);
    }

    /**
     * Insert a user into the database, insertion failure cause returning null
     * @param userEntityDTO
     * @return The inserted user object, null of insertion failure
     */
    @Override
    public UserEntityDTO createOrUpdateUser(UserEntityDTO userEntityDTO) {
        try {
            UserEntity save = userEntityRepository.save(dtoToEntity(userEntityDTO));
            return entityToDto(save);
        } catch (Exception e) {
            throw new RuntimeException("Insertion Failure");
        }
    }

    @Override
    public UserEntityDTO updateUserByEmail(UserEntityDTO userEntityDTO, String email) {
        UserEntity userEntityById = userEntityRepository.findUserEntityByEmail(email);
        if (userEntityById == null) {
            return null;
        }
        try {
            UserEntity save = userEntityRepository.save(dtoToEntity(userEntityDTO));
            return entityToDto(save);
        } catch (Exception e) {
            throw new RuntimeException("Insertion Failure");
        }
    }


    @Override
    public UserEntityDTO updateUserById(UserEntityDTO userEntityDTO, Long id) {
        UserEntity userEntityById = userEntityRepository.findUserEntityById(id);
        if (userEntityById == null) {
            return null;
        }
        try {
            UserEntity save = userEntityRepository.save(dtoToEntity(userEntityDTO));
            return entityToDto(save);
        } catch (Exception e) {
            throw new RuntimeException("Insertion Failure");
        }
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        UserEntity userEntityById = userEntityRepository.findUserEntityById(id);
        if (userEntityById == null) {
            throw new NoSuchElementException("User Id doesn't exist");
        }
        try {
            userEntityRepository.deleteUserEntityById(id);
        } catch (Exception e) {
            throw new RuntimeException("Deletion Failure");
        }
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

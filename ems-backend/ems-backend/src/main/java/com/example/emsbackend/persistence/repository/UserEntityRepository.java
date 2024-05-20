package com.example.emsbackend.persistence.repository;

import com.example.emsbackend.persistence.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findUserEntityById(Integer uid);

    List<UserEntity> findUserEntitiesByRoleId(Integer rid);

}

// 'password123'

// jane2024

// alicePwd

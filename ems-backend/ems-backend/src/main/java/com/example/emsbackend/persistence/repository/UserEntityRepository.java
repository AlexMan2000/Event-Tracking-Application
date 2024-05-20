package com.example.emsbackend.persistence.repository;

import com.example.emsbackend.persistence.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findUserEntityById(Long uid);

    List<UserEntity> findUserEntitiesByRoleId(Long rid);

    UserEntity deleteUserEntityById(Long id);

}

// 'password123'

// jane2024

// alicePwd

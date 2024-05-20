package com.example.emsbackend.persistence.repository;

import com.example.emsbackend.persistence.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {


    UserEntity findUserEntityById(Long uid);

    UserEntity findUserEntityByEmail(String email);

    List<UserEntity> findUserEntitiesByRoleId(Long rid);

    @Query("SELECT u FROM UserEntity u WHERE u.email = :email AND u.passwordHash = :passwordHash")
    UserEntity findByEmailAndPassword(@Param("email") String email, @Param("passwordHash") String passwordHash);


    @Transactional
    @Modifying
    UserEntity deleteUserEntityById(Long id);

}

// 'password123'

// jane2024

// alicePwd

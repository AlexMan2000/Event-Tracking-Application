package com.example.emsbackend.persistence.repository;

import com.example.emsbackend.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findRoleEntitiesById(Long id);


}

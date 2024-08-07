package com.example.emsbackend.repository.users;

import com.example.emsbackend.entity.users.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findRoleEntitiesById(Long id);


}

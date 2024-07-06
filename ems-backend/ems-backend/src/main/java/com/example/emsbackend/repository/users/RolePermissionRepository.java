package com.example.emsbackend.repository.users;

import com.example.emsbackend.entity.users.RolePermissionEntity;
import com.example.emsbackend.entity.users.RolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, RolePermissionId> {
}

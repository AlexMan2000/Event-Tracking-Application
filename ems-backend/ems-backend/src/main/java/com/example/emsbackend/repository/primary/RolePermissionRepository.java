package com.example.emsbackend.repository.primary;

import com.example.emsbackend.entity.primary.RolePermissionEntity;
import com.example.emsbackend.entity.primary.RolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, RolePermissionId> {
}

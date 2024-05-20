package com.example.emsbackend.persistence.repository;

import com.example.emsbackend.persistence.entity.RolePermissionEntity;
import com.example.emsbackend.persistence.entity.RolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, RolePermissionId> {
}

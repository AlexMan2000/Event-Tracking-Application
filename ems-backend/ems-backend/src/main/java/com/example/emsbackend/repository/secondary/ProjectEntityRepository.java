package com.example.emsbackend.repository.secondary;

import com.example.emsbackend.entity.secondary.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectEntityRepository extends JpaRepository<ProjectEntity, String> {
}

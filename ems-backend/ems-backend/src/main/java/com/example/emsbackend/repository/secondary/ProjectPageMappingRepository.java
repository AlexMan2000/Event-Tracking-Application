package com.example.emsbackend.repository.secondary;

import com.example.emsbackend.entity.secondary.ProjectPageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectPageMappingRepository extends JpaRepository<ProjectPageEntity, ProjectPageId> {
}

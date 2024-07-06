package com.example.emsbackend.repository.events;

import com.example.emsbackend.entity.events.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectEntityRepository extends JpaRepository<ProjectEntity, String> {
}

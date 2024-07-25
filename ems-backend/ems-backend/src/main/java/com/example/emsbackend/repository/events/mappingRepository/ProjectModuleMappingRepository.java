package com.example.emsbackend.repository.events.mappingRepository;

import com.example.emsbackend.entity.events.mappingEntity.ProjectModuleEntity;
import com.example.emsbackend.repository.events.compositeId.ProjectModuleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProjectModuleMappingRepository extends JpaRepository<ProjectModuleEntity, ProjectModuleId> {

    @Query(value = "select module_id from project_module where project_id = ?1", nativeQuery = true)
    Set<Long> getAllModuleIdsForProjectById(Long projectId);

    @Query(value = "select project_id from project_module where module_id = ?1", nativeQuery = true)
    Set<Long> getAllProjectIdsForModuleById(Long module_id);
}

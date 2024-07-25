package com.example.emsbackend.repository.events.mappingRepository;

import com.example.emsbackend.entity.events.mappingEntity.ProjectParameterEntity;
import com.example.emsbackend.repository.events.compositeId.ProjectParameterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface ProjectParameterMappingRepository extends JpaRepository<ProjectParameterEntity, ProjectParameterId> {

    @Query(value = "select parameter_id from project_parameter where project_id = ?1", nativeQuery = true)
    Set<Long> getAllParameterIdsForProjectById(Long projectId);

    @Query(value = "select project_id from project_parameter where parameter_id = ?1", nativeQuery = true)
    Set<Long> getAllProjectIdsForParameterById(Long parameter_id);
}

package com.example.emsbackend.repository.events.mappingRepository;

import com.example.emsbackend.entity.events.mappingEntity.ModuleParameterEntity;
import com.example.emsbackend.repository.events.compositeId.ModuleParameterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface ModuleParameterMappingRepository extends JpaRepository<ModuleParameterEntity, ModuleParameterId> {
    @Query(value = "select parameter_id from module_parameter where module_id = ?1", nativeQuery = true)
    Set<Long> getAllParameterIdsForModuleById(Long moduleId);

    @Query(value = "select module_id from module_parameter where parameter_id = ?1", nativeQuery = true)
    Set<Long> getAllModuleIdsForParamaterById(Long parameterId);
}

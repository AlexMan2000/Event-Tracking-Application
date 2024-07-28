package com.example.emsbackend.repository.events.mappingRepository;

import com.example.emsbackend.entity.events.mappingEntity.ModulePageEntity;
import com.example.emsbackend.repository.events.compositeId.ModulePageId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ModulePageMappingRepository extends JpaRepository<ModulePageEntity, ModulePageId> {
    @Query(value = "select page_id from module_page where module_id = ?1", nativeQuery = true)
    Set<Long> getAllPageIdsForModuleById(Long moduleId);

    @Query(value = "select module_id from module_page where page_id = ?1", nativeQuery = true)
    Set<Long> getAllModuleIdsForPageById(Long pageId);
}

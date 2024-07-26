package com.example.emsbackend.repository.events.entityRepository;

import com.example.emsbackend.dto.events.getDTO.GetIdentifiersDTO;
import com.example.emsbackend.entity.events.entityEntity.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleEntityRepository extends JpaRepository<ModuleEntity, Long> {

    @Query(value = "SELECT id, module_identifier as identifierCode  from module", nativeQuery = true)
    List<GetIdentifiersDTO> findAllMetaData();
}

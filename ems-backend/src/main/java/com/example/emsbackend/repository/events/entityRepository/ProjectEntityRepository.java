package com.example.emsbackend.repository.events.entityRepository;

import com.example.emsbackend.dto.events.getDTO.GetIdentifiersDTO;
import com.example.emsbackend.entity.events.entityEntity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProjectEntityRepository extends JpaRepository<ProjectEntity, Long> {

    @Query(value = "SELECT id, identifier_code as identifierCode from project", nativeQuery = true)
    List<GetIdentifiersDTO> findAllMetaData();
}

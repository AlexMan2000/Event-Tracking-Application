package com.example.emsbackend.repository.events.entityRepository;

import com.example.emsbackend.dto.events.getDTO.GetIdentifiersDTO;
import com.example.emsbackend.entity.events.entityEntity.ParameterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParameterEntityRepository extends JpaRepository<ParameterEntity, Long> {

    @Query(value = "SELECT id, identifier_code as identifierCode from parameter", nativeQuery = true)
    List<GetIdentifiersDTO> findAllMetaData();

}

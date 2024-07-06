package com.example.emsbackend.repository.events;

import com.example.emsbackend.entity.events.ParameterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ParameterEntityRepository extends JpaRepository<ParameterEntity, String> {

    @Query(value="SELECT * FROM parameter WHERE identifier_code = ?1", nativeQuery = true)
    ParameterEntity findParameterEntityByIdentifierCode(String id);

    @Query(value="SELECT identifier_code FROM parameter", nativeQuery = true)
    List<String> findAllParameterIdentifers();

}

package com.example.emsbackend.repository.secondary;

import com.example.emsbackend.entity.secondary.ParameterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ParameterEntityRepository extends JpaRepository<ParameterEntity, String> {

    @Query(value="SELECT * FROM parameter WHERE identifier_code = ?1", nativeQuery = true)
    ParameterEntity findParameterEntityByIdentifierCode(String id);

}

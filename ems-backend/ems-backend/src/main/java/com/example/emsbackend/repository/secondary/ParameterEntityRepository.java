package com.example.emsbackend.repository.secondary;

import com.example.emsbackend.entity.secondary.ParameterEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ParameterEntityRepository extends JpaRepository<ParameterEntity, String> {

    ParameterEntity findParameterEntitiesByIdentifier_code(String id);

}

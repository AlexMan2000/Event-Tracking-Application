package com.example.emsbackend.repository.events.entityRepository;

import com.example.emsbackend.entity.events.entityEntity.ParameterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterEntityRepository extends JpaRepository<ParameterEntity, Long> {



}

package com.example.emsbackend.persistence.repository;


import com.example.emsbackend.persistence.entity.DagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DagEntityRepository extends JpaRepository<DagEntity, Long> {

    List<DagEntity> findByOwners(String ownerName);

    DagEntity findFirstById(Long id);
}

package com.example.emsbackend.repository.users;


import com.example.emsbackend.entity.users.DagEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DagEntityRepository extends JpaRepository<DagEntity, Long> {

    List<DagEntity> findByOwners(String ownerName);

    DagEntity findFirstByDagId(String id);

    DagEntity findByDagId(String id);

    boolean existsByDagId(String id);

    @Transactional
    void deleteByDagId(String id);
}

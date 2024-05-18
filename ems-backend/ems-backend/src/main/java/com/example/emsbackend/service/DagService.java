package com.example.emsbackend.service;

import com.example.emsbackend.api.dto.DagEntityDTO;
import com.example.emsbackend.persistence.entity.DagEntity;
import com.example.emsbackend.persistence.repository.DagEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DagService {

    private final DagEntityRepository dagRepository;

    @Autowired
    public DagService(DagEntityRepository dagRepository) {
        this.dagRepository = dagRepository;
    }

    public DagEntityDTO getDagDtoById(Long id) {
        DagEntity dagEntity = dagRepository.findFirstById(id);

        return DagEntityDTO.builder()
                .dagId(dagEntity.getDagId())
                .isSubDag(dagEntity.getIsSubdag())
                .isActive(dagEntity.getIsActive())
                .isPaused(dagEntity.getIsPaused())
                .owner(dagEntity.getOwners()).build();

    }

    public List<DagEntityDTO> getDagDtoByOwners(String name) {
        List<DagEntity> dagEntities = dagRepository.findByOwners(name);

        List<DagEntityDTO> res = new ArrayList<>();
        for (DagEntity dagEntity: dagEntities) {
            res.add(DagEntityDTO.builder()
                    .dagId(dagEntity.getDagId())
                    .isSubDag(dagEntity.getIsSubdag())
                    .isActive(dagEntity.getIsActive())
                    .isPaused(dagEntity.getIsPaused())
                    .owner(dagEntity.getOwners()).build());
        }

        return res;

    }

}

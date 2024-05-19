package com.example.emsbackend.service.impl;

import com.example.emsbackend.api.dto.DagEntityDTO;
import com.example.emsbackend.persistence.entity.DagEntity;
import com.example.emsbackend.persistence.repository.DagEntityRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class DagService implements com.example.emsbackend.service.DagService {

    private final DagEntityRepository dagRepository;
    private final ModelMapper modelMapper;
    private final AtomicLong longGenerator;

    // IOC
    @Autowired
    public DagService(DagEntityRepository dagRepository
            , ModelMapper modelMapper
            , AtomicLong longGenerator) {
        this.dagRepository = dagRepository;
        this.modelMapper = modelMapper;
        this.longGenerator = longGenerator;
    }

    /*  GET APIs */
    @Override
    public DagEntityDTO getDagDtoById(String dag_id) {
        DagEntity dagEntity = dagRepository.findFirstByDagId(dag_id);
        return dagEntity != null ? entityToDto(dagEntity): null;
    }

    @Override
    public List<DagEntityDTO> getDagDtoByOwners(String name) {
        List<DagEntity> dagEntities = dagRepository.findByOwners(name);
        return dagEntities.size() != 0 ? dagEntities.stream()
                            .map(this::entityToDto)
                            .collect(Collectors.toList()): null;
    }


    /* POST APIs */
    @Override
    public DagEntityDTO createOrUpdateDagEntity(DagEntityDTO dagEntityDTO) {

        DagEntity dagEntity = dtoToEntity(dagEntityDTO);
        dagEntity.setId(longGenerator.incrementAndGet());
        try {
            DagEntity savedDagEntity = dagRepository.save(dagEntity);
            return entityToDto(savedDagEntity);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Failed to save the entity due to integrity violation caused by corrupt input");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unknown error occurred during insertion");
        }
    }

    @Override
    public boolean checkIDDuplicate(String id) {
        return dagRepository.existsByDagId(id);
    }






    /* -------------------- Helper functions ----------------*/
    private DagEntityDTO entityToDto(DagEntity dagEntity) {
        return modelMapper.map(dagEntity, DagEntityDTO.class);
    }


    private DagEntity dtoToEntity(DagEntityDTO dagDto) {
        return modelMapper.map(dagDto, DagEntity.class);
    }

}

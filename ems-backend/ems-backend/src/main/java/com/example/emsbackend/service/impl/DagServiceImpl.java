package com.example.emsbackend.service.impl;

import com.example.emsbackend.dto.primary.DagEntityDTO;
import com.example.emsbackend.entity.primary.DagEntity;
import com.example.emsbackend.repository.primary.DagEntityRepository;
import com.example.emsbackend.service.DagService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class DagServiceImpl implements DagService {

    private final DagEntityRepository dagRepository;
    private final ModelMapper modelMapper;
    private final AtomicLong longGenerator;

    // IOC
    @Autowired
    public DagServiceImpl(DagEntityRepository dagRepository
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

    @Override
    public List<DagEntityDTO> getAllDags() {
        return dagRepository.findAll().stream().map(dagEntity -> entityToDto(dagEntity)).collect(Collectors.toList());
    }


    @Override
    public boolean checkIDDuplicate(String id) {
        return dagRepository.existsByDagId(id);
    }




    /* POST APIs */
    @Override
    public DagEntityDTO createOrUpdateDagEntity(DagEntityDTO dagEntityDTO) {

        DagEntity dagEntity = dtoToEntity(dagEntityDTO);
        dagEntity.setId(longGenerator.incrementAndGet());
        try {
            // If dagEntity contains the primary key, and the primary key exists in DB, perform update; otherwise, perform insertion
            DagEntity savedDagEntity = dagRepository.save(dagEntity);
            return entityToDto(savedDagEntity);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Failed to save the entity due to integrity violation caused by corrupt input");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unknown error occurred during insertion");
        }
    }



    /* PUT APIs */
    @Override
    public DagEntityDTO updateDagEntity(String dagId, DagEntityDTO updatedDagEntityDto) {
        DagEntity dagEntity = dagRepository.findByDagId(dagId);
        if (dagEntity == null) {
            return null;
        }
        Long entityId = dagEntity.getId();
        DagEntity updatedDagEntity = dtoToEntity(updatedDagEntityDto);
        BeanUtils.copyProperties(updatedDagEntity, dagEntity);
        dagEntity.setId(entityId);
        DagEntity updatedDagEntity_ = dagRepository.save(dagEntity);
        return entityToDto(updatedDagEntity_);
    }



    /* DELETE APIs */
    public DagEntityDTO deleteDagEntity(String dagId) {
        DagEntity dagEntity = dagRepository.findByDagId(dagId);
        if (dagEntity == null) {
            return null;
        }
        dagRepository.deleteByDagId(dagId);
        return entityToDto(dagEntity);
    }


    /* -------------------- Helper functions ----------------*/
    private DagEntityDTO entityToDto(DagEntity dagEntity) {
        return modelMapper.map(dagEntity, DagEntityDTO.class);
    }


    private DagEntity dtoToEntity(DagEntityDTO dagDto) {
        return modelMapper.map(dagDto, DagEntity.class);
    }

}

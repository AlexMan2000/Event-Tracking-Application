package com.example.emsbackend.service.impl;

import com.example.emsbackend.api.dto.DagEntityDTO;
import com.example.emsbackend.persistence.entity.DagEntity;
import com.example.emsbackend.persistence.repository.DagEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DagService implements com.example.emsbackend.service.DagService {

    private final DagEntityRepository dagRepository;
    private final ModelMapper modelMapper;

    // IOC
    @Autowired
    public DagService(DagEntityRepository dagRepository, ModelMapper modelMapper) {
        this.dagRepository = dagRepository;
        this.modelMapper = modelMapper;
    }

    /*  GET APIs */
    public DagEntityDTO getDagDtoById(String dag_id) {
        DagEntity dagEntity = dagRepository.findFirstByDagId(dag_id);
        return dagEntity != null ? entityToDto(dagEntity): null;
    }

    public List<DagEntityDTO> getDagDtoByOwners(String name) {
        List<DagEntity> dagEntities = dagRepository.findByOwners(name);
        return dagEntities.size() != 0 ? dagEntities.stream()
                            .map(this::entityToDto)
                            .collect(Collectors.toList()): null;
    }



    /* POST APIs */









    /* -------------------- Helper functions ----------------*/
    private DagEntityDTO entityToDto(DagEntity dagEntity) {
        return modelMapper.map(dagEntity, DagEntityDTO.class);
    }


    private DagEntity dtoToEntity(DagEntityDTO dagDto) {
        return modelMapper.map(dagDto, DagEntity.class);
    }

}

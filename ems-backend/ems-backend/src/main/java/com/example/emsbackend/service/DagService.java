package com.example.emsbackend.service;

import com.example.emsbackend.dto.DagEntityDTO;

import java.util.List;

public interface DagService {

    /* GET APIs */
     DagEntityDTO getDagDtoById(String dag_id);
     List<DagEntityDTO> getDagDtoByOwners(String name);
     List<DagEntityDTO> getAllDags();

     boolean checkIDDuplicate(String id);


    /* POST APIs */
    DagEntityDTO createOrUpdateDagEntity(DagEntityDTO dagEntityDTO);


    /* PUT APIs */
    DagEntityDTO updateDagEntity(String dagId, DagEntityDTO updatedDagEntityDto);


    /* DELETE APIs */
    DagEntityDTO deleteDagEntity(String dagId);





}

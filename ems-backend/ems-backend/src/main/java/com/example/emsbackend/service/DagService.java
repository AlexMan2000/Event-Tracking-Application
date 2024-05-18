package com.example.emsbackend.service;

import com.example.emsbackend.api.dto.DagEntityDTO;

import java.util.List;

public interface DagService {

    /* GET APIs */
     DagEntityDTO getDagDtoById(String dag_id);
     List<DagEntityDTO> getDagDtoByOwners(String name);


    /* POST APIs */



    /* DELETE APIs */





}

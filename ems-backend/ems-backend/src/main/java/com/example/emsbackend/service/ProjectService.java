package com.example.emsbackend.service;

import com.example.emsbackend.dto.secondary.PageEntityDTO;

import java.util.List;

public interface ProjectService {

    /**
     * Get all the pages that belong to the project by projectID
     * @param projectID
     * @return
     */
    List<PageEntityDTO> getAllPagesOfProjectByID(String projectID);



}

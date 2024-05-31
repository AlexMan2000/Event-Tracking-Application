package com.example.emsbackend.service;

import com.example.emsbackend.dto.secondary.PageEntityDTO;
import com.example.emsbackend.dto.secondary.ProjectEntityDTO;
import com.example.emsbackend.entity.secondary.PageEntity;
import com.example.emsbackend.entity.secondary.ProjectEntity;

import java.util.List;

public interface ProjectService {

    /**
     * Get all the pages that belong to the project by projectID
     * @param projectID
     * @return
     */
    List<PageEntityDTO> getAllPagesOfProjectByID(String projectID);


    ProjectEntityDTO convertEntityToDTO(ProjectEntity inputObj);

    ProjectEntity convertDTOToEntity(ProjectEntityDTO inputObj);

}

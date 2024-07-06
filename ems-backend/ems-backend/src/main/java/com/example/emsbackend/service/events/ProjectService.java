package com.example.emsbackend.service.events;

import com.example.emsbackend.dto.events.PageEntityDTO;
import com.example.emsbackend.dto.events.ProjectEntityDTO;
import com.example.emsbackend.entity.events.ProjectEntity;

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

package com.example.emsbackend.service.events;

import com.example.emsbackend.commons.status.Message;
import com.example.emsbackend.criteria_utils.searching.SearchCriteria;
import com.example.emsbackend.dto.events.getDTO.GetIdentifiersDTO;
import com.example.emsbackend.dto.events.getDTO.ProjectEntityGetObjectDTO;
import com.example.emsbackend.dto.events.modifyDTO.ProjectEntityUpdateObjectDTO;

import java.util.List;

public interface ProjectService {

    List<ProjectEntityGetObjectDTO> getAllProjects();

    List<ProjectEntityGetObjectDTO> getAllProjectsFiltered(SearchCriteria searchCriteria);

    List<GetIdentifiersDTO> getAllMetaData();

    Message createProject(ProjectEntityUpdateObjectDTO projectEntityUpdateObjectDTO);

    Message updateProjectById(Long projectId, ProjectEntityUpdateObjectDTO projectEntityUpdateObjectDTO);

}

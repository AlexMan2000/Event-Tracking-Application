package com.example.emsbackend.service.events.impl;

import com.example.emsbackend.commons.enums.StatusCode;
import com.example.emsbackend.commons.status.Message;
import com.example.emsbackend.criteria_utils.searching.SearchCriteria;
import com.example.emsbackend.criteria_utils.searching.impl.ProjectEntitySearchImpl;
import com.example.emsbackend.dto.events.getDTO.GetIdentifiersDTO;
import com.example.emsbackend.dto.events.getDTO.ProjectEntityGetObjectDTO;
import com.example.emsbackend.dto.events.modifyDTO.ProjectEntityUpdateObjectDTO;
import com.example.emsbackend.entity.events.entityEntity.ModuleEntity;
import com.example.emsbackend.entity.events.entityEntity.ParameterEntity;
import com.example.emsbackend.entity.events.entityEntity.ProjectEntity;
import com.example.emsbackend.repository.events.entityRepository.ModuleEntityRepository;
import com.example.emsbackend.repository.events.entityRepository.ParameterEntityRepository;
import com.example.emsbackend.repository.events.entityRepository.ProjectEntityRepository;
import com.example.emsbackend.repository.events.mappingRepository.ProjectModuleMappingRepository;
import com.example.emsbackend.repository.events.mappingRepository.ProjectParameterMappingRepository;
import com.example.emsbackend.service.events.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProjectEntityRepository projectEntityRepository;

    @Autowired
    private ModuleEntityRepository moduleEntityRepository;

    @Autowired
    private ParameterEntityRepository parameterEntityRepository;

    @Autowired
    private ProjectModuleMappingRepository projectModuleMappingRepository;

    @Autowired
    private ProjectParameterMappingRepository projectParameterMappingRepository;

    @Autowired
    private ProjectEntitySearchImpl projectSearchFilter;


    @Override
    @Transactional
    public List<ProjectEntityGetObjectDTO> getAllProjects() {
        return projectEntityRepository.findAll().stream()
                .map(elem -> modelMapper.map(elem, ProjectEntityGetObjectDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectEntityGetObjectDTO> getAllProjectsFiltered(SearchCriteria searchCriteria) {
        return projectSearchFilter
                .getItemsFiltered(searchCriteria, ProjectEntity.class)
                .stream()
                .map(elem -> modelMapper.map(elem, ProjectEntityGetObjectDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<GetIdentifiersDTO> getAllMetaData() {
        return projectEntityRepository.findAllMetaData();
    }


    @Override
    public Message createProject(ProjectEntityUpdateObjectDTO projectEntityUpdateObjectDTO) {
        try {
            ProjectEntity projectEntity = recoverProjectEntityFromUpdateDTO(projectEntityUpdateObjectDTO);
            projectEntityRepository.save(projectEntity);
        }catch (Exception e) {
            e.printStackTrace();
            return new Message(StatusCode.CREATE_FAILURE, "Error Creating records");
        }
        return new Message(StatusCode.OK, "Successfully creating records");
    }

    @Override
    public Message updateProjectById(Long projectId, ProjectEntityUpdateObjectDTO projectEntityUpdateObjectDTO) {
        try {
            ProjectEntity projectEntity = recoverProjectEntityFromUpdateDTO(projectEntityUpdateObjectDTO);
            projectEntityRepository.save(projectEntity);
        }catch (Exception e) {
            e.printStackTrace();
            return new Message(StatusCode.CREATE_FAILURE, "Error Updating records");
        }
        return new Message(StatusCode.OK, "Error Updating records");
    }


    private ProjectEntity recoverProjectEntityFromUpdateDTO(ProjectEntityUpdateObjectDTO projectEntityUpdateObjectDTO) {
        Long projectId = projectEntityUpdateObjectDTO.getId();

        projectEntityRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found" + projectId));


        Set<ModuleEntity> moduleEntities = new HashSet<>();
        Set<ParameterEntity> parameterEntities = new HashSet<>();

        ProjectEntity projectEntity = this.modelMapper.map(projectEntityUpdateObjectDTO, ProjectEntity.class);
        for (Long moduleId: projectEntityUpdateObjectDTO.getModuleIds()) {
            ModuleEntity moduleEntity = moduleEntityRepository.findById(moduleId).orElseThrow(() -> new RuntimeException("Module not found" + moduleId));
            moduleEntities.add(moduleEntity);
        }
        for (Long parameterId: projectEntityUpdateObjectDTO.getParameterIds()) {
            ParameterEntity parameterEntity = parameterEntityRepository.findById(parameterId).orElseThrow(() -> new RuntimeException("Module not found" + parameterId));
            parameterEntities.add(parameterEntity);
        }

        projectEntity.setModules(moduleEntities);
        projectEntity.setParams(parameterEntities);

        return projectEntity;
    }
}

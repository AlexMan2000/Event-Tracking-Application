package com.example.emsbackend.service.events.impl;

import com.example.emsbackend.dto.events.PageEntityDTO;
import com.example.emsbackend.dto.events.ProjectEntityDTO;
import com.example.emsbackend.entity.events.ProjectEntity;
import com.example.emsbackend.repository.events.ProjectEntityRepository;
import com.example.emsbackend.repository.events.ProjectPageMappingRepository;
import com.example.emsbackend.service.events.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProjectEntityRepository projectEntityRepository;


    @Autowired
    private ProjectPageMappingRepository projectPageMappingRepository;


    public ProjectEntityDTO insertOrUpdateProject(ProjectEntityDTO projectEntityDTO) {
        try {
            ProjectEntity save = projectEntityRepository.save(convertDTOToEntity(projectEntityDTO));
            return convertEntityToDTO(save);
        } catch (Exception e) {
            throw new RuntimeException("Insertion Failure");
        }
    }



    @Override
    public List<PageEntityDTO> getAllPagesOfProjectByID(String projectID) {
        return projectPageMappingRepository.getAllPagesOfProjectByID(projectID).stream()
                .map(elem -> this.modelMapper.map(elem, PageEntityDTO.class))
                .toList();
    }

    @Override
    public ProjectEntityDTO convertEntityToDTO(ProjectEntity inputObj) {
        return modelMapper.map(inputObj, ProjectEntityDTO.class);
    }

    @Override
    public ProjectEntity convertDTOToEntity(ProjectEntityDTO inputObj) {
        return modelMapper.map(inputObj, ProjectEntity.class);
    }
}

package com.example.emsbackend.service.impl;

import cn.hutool.crypto.Mode;
import com.example.emsbackend.dto.secondary.PageEntityDTO;
import com.example.emsbackend.dto.secondary.ProjectEntityDTO;
import com.example.emsbackend.entity.secondary.PageEntity;
import com.example.emsbackend.entity.secondary.ProjectEntity;
import com.example.emsbackend.repository.secondary.ProjectEntityRepository;
import com.example.emsbackend.repository.secondary.ProjectPageMappingRepository;
import com.example.emsbackend.service.PageService;
import com.example.emsbackend.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectEntityRepository projectEntityRepository;


    @Autowired
    private PageService pageService;


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
                .map(elem -> pageService.convertEntityToDTO(elem))
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

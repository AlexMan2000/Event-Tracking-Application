package com.example.emsbackend.service.events.impl;

import com.example.emsbackend.commons.status.Message;
import com.example.emsbackend.criteria_utils.searching.ModuleEntitySearchCriteria;
import com.example.emsbackend.criteria_utils.searching.impl.ModuleEntitySearchImpl;
import com.example.emsbackend.dto.events.getDTO.GetIdentifiersDTO;
import com.example.emsbackend.dto.events.getDTO.ModuleEntityGetObjectDTO;
import com.example.emsbackend.dto.events.getDTO.ProjectEntityGetObjectDTO;
import com.example.emsbackend.dto.events.modifyDTO.ModuleEntityUpdateObjectDTO;
import com.example.emsbackend.entity.events.entityEntity.ModuleEntity;
import com.example.emsbackend.repository.events.entityRepository.ModuleEntityRepository;
import com.example.emsbackend.service.events.ModuleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ModuleEntitySearchImpl moduleSearchFilter;

    @Autowired
    private ModuleEntityRepository moduleEntityRepository;

    @Override
    public List<ModuleEntityGetObjectDTO> getAllModules() {
        return moduleEntityRepository.findAll().stream()
                .map(elem -> this.modelMapper.map(elem, ModuleEntityGetObjectDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ModuleEntityGetObjectDTO> getAllModulesFiltered(ModuleEntitySearchCriteria searchCriteria) {
        return moduleSearchFilter
                .getItemsFiltered(searchCriteria, ModuleEntity.class)
                .stream()
                .map(elem -> modelMapper.map(elem, ModuleEntityGetObjectDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GetIdentifiersDTO> getAllMetaData() {
        return moduleEntityRepository.findAllMetaData().stream()
                .map(elem -> this.modelMapper.map(elem, GetIdentifiersDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ModuleEntityGetObjectDTO getModuleById(Long moduleId) {
        return moduleEntityRepository.findById(moduleId)
                .map(elem -> this.modelMapper.map(elem, ModuleEntityGetObjectDTO.class))
                .orElseThrow(() -> {throw new RuntimeException("Project with" + moduleId + "not found!");});
    }

    @Override
    public Message createModule(ModuleEntityUpdateObjectDTO projectEntityUpdateObjectDTO) {
        return null;
    }

    @Override
    public Message updateModule(ModuleEntityUpdateObjectDTO projectEntityUpdateObjectDTO) {
        return null;
    }
}

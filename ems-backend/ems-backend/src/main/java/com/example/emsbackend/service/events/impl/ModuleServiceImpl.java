package com.example.emsbackend.service.events.impl;

import com.example.emsbackend.dto.events.getDTO.GetIdentifiersDTO;
import com.example.emsbackend.repository.events.entityRepository.ModuleEntityRepository;
import com.example.emsbackend.service.events.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ModuleServiceImpl implements ModuleService {


    @Autowired
    private ModuleEntityRepository moduleEntityRepository;


    @Override
    public List<GetIdentifiersDTO> getAllMetaData() {
        return moduleEntityRepository.findAllMetaData();
    }
}

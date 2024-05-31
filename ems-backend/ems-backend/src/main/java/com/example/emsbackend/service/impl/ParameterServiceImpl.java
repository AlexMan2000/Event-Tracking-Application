package com.example.emsbackend.service.impl;

import com.example.emsbackend.dto.secondary.EventEntityDTO;
import com.example.emsbackend.dto.secondary.ParameterEntityDTO;
import com.example.emsbackend.entity.secondary.ParameterEntity;
import com.example.emsbackend.repository.secondary.EventEntityRepository;
import com.example.emsbackend.repository.secondary.EventParameterMappingRepository;
import com.example.emsbackend.repository.secondary.ParameterEntityRepository;
import com.example.emsbackend.service.ParameterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ParameterServiceImpl implements ParameterService {

    @Autowired
    private ParameterEntityRepository parameterEntityRepository;


    @Autowired
    private EventEntityRepository eventEntityRepository;

    @Autowired
    private EventServiceImpl eventService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EventParameterMappingRepository eventParameterMappingRepository;

    public ParameterEntityDTO getParameterById(String parameterId) {
        ParameterEntity parameterEntity = parameterEntityRepository.findParameterEntitiesByIdentifier_code(parameterId);

        return modelMapper.map(parameterEntity, ParameterEntityDTO.class);
    }



    @Override
    public void createParameter(ParameterEntityDTO parameterEntityDTO) {

    }

    /**
     * Return all the events that this event parameter is registered to.
     * @param paraID
     * @return
     */
    @Override
    public List<EventEntityDTO> getEventForParameterByPID(String paraID) {
        ParameterEntityDTO parameter = getParameterById(paraID);
        assert parameter.getParameterType().equals("Event");
        List<String> allEventEntitiesForParaById = eventParameterMappingRepository.findAllEventEntitiesForParaById(paraID);
        return allEventEntitiesForParaById.stream()
                .map(elem -> eventEntityRepository.findEventEntityByIdentifierCode(elem))
                .map(elem -> eventService.convertEntityToDTO(elem)).toList();
    }



    ParameterEntityDTO entityToDTO(ParameterEntity parameterEntity) {
        return modelMapper.map(parameterEntity, ParameterEntityDTO.class);
    }

    ParameterEntity DTOtoEntity(ParameterEntityDTO parameterEntityDTO) {
        return modelMapper.map(parameterEntityDTO, ParameterEntity.class);
    }

    @Override
    public Boolean validateParameterValue(String inputValue, String parameterID) {
        return null;
    }
}

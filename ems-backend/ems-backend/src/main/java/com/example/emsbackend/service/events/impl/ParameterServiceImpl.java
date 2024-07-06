package com.example.emsbackend.service.events.impl;

import com.example.emsbackend.criteria_utils.searching.impl.ParameterEntitySearchCriteria;
import com.example.emsbackend.criteria_utils.searching.impl.ParameterEntitySearchImpl;
import com.example.emsbackend.dto.events.EventEntityDTO;
import com.example.emsbackend.dto.events.ParameterEntityDTO;
import com.example.emsbackend.entity.events.ParameterEntity;
import com.example.emsbackend.repository.events.EventEntityRepository;
import com.example.emsbackend.repository.events.EventParameterMappingRepository;
import com.example.emsbackend.repository.events.ParameterEntityRepository;
import com.example.emsbackend.service.events.ParameterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParameterServiceImpl implements ParameterService {


    @Autowired
    private ParameterEntityRepository parameterEntityRepository;


    @Autowired
    private ParameterEntitySearchImpl parameterEntitySearch;

    @Autowired
    private EventEntityRepository eventEntityRepository;

    @Autowired
    private EventServiceImpl eventService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EventParameterMappingRepository eventParameterMappingRepository;


    @Override
    public void createParameter(ParameterEntityDTO parameterEntityDTO) {

    }

    @Override
    public List<String> getAllParameterIdentifiers() {
        return parameterEntityRepository.findAllParameterIdentifers();
    }

    @Override
    public List<ParameterEntityDTO> getAllParametersFiltered(ParameterEntitySearchCriteria searchCriteria) {
        return parameterEntitySearch.getItemsFiltered(searchCriteria, ParameterEntity.class).stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    @Override
    public void createParameters(List<ParameterEntityDTO> parameterEntityDTOList) {
        ParameterService.super.createParameters(parameterEntityDTOList);
    }

    @Override
    public ParameterEntityDTO getParameterById(String id) {
        return entityToDTO(parameterEntityRepository.findParameterEntityByIdentifierCode(id));
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

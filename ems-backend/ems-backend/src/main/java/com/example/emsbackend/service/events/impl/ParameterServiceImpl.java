package com.example.emsbackend.service.events.impl;

import com.example.emsbackend.criteria_utils.searching.ParameterEntitySearchCriteria;
import com.example.emsbackend.criteria_utils.searching.impl.ParameterEntitySearchImpl;
import com.example.emsbackend.dto.events.entityDTO.EventEntityDTO;
import com.example.emsbackend.dto.events.entityDTO.ParameterEntityDTO;
import com.example.emsbackend.entity.events.entityEntity.ParameterEntity;
import com.example.emsbackend.repository.events.entityRepository.EventEntityRepository;
import com.example.emsbackend.repository.events.mappingRepository.EventParameterMappingRepository;
import com.example.emsbackend.repository.events.entityRepository.ParameterEntityRepository;
import com.example.emsbackend.service.events.ParameterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
        return parameterEntityRepository.findAll().stream().map(elem -> elem.getIdentifierCode()).collect(Collectors.toList());
    }


    public List<Long> getAllParameterIds() {
        return parameterEntityRepository.findAll().stream().map(elem -> elem.getId()).collect(Collectors.toList());
    }


    public List<Map<String, String>> getDropdownInformation() {
        return parameterEntityRepository.findAll().stream().map(elem -> {
            Map<String, String> temp = new HashMap<>();
            temp.put("id", elem.getId().toString());
            temp.put("identifierCode", elem.getIdentifierCode());
            return temp;
        }).collect(Collectors.toList());
    }

    /**
     * Get all the parameter obj with filter keywords
     * @param searchCriteria
     * @return
     */
    @Override
    public List<ParameterEntityDTO> getAllParametersFiltered(ParameterEntitySearchCriteria searchCriteria) {
        return parameterEntitySearch.getItemsFiltered(searchCriteria, ParameterEntity.class).stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    @Override
    public void createParameters(List<ParameterEntityDTO> parameterEntityDTOList) {
        ParameterService.super.createParameters(parameterEntityDTOList);
    }

    @Override
    public ParameterEntityDTO getParameterById(Long id) {
        Optional<ParameterEntity> byId = parameterEntityRepository.findById(id);
        if (byId.isPresent()) {
            return entityToDTO(byId.get());
        }
        return null;
    }

    /**
     * Return all the event entities that this event parameter is registered to.
     * @param paraID Id of parameter entity
     * @return A list of event entityDTO associated with the given parameter ID
     */
    @Override
    public List<EventEntityDTO> getEventEntityDTOListByParameterID(Long paraID) {
        ParameterEntityDTO parameter = getParameterById(paraID);
        assert parameter.getParameterType().equals("Event");
        List<Long> allEventEntitiesForParaById = eventParameterMappingRepository.findAllEventEntitiesForParaById(paraID);
        return allEventEntitiesForParaById.stream()
                .map(elem -> eventEntityRepository.findById(elem))
                .map(elem -> elem.map(eventEntity -> eventService.convertEntityToDTO(eventEntity)).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }



    ParameterEntityDTO entityToDTO(ParameterEntity parameterEntity) {
        return modelMapper.map(parameterEntity, ParameterEntityDTO.class);
    }

    ParameterEntity DTOtoEntity(ParameterEntityDTO parameterEntityDTO) {
        return modelMapper.map(parameterEntityDTO, ParameterEntity.class);
    }

    @Override
    public Boolean validateParameterValue(String inputValue, Long parameterID) {
        return null;
    }
}

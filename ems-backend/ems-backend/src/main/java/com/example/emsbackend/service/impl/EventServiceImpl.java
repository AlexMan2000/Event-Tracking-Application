package com.example.emsbackend.service.impl;

import com.example.emsbackend.dto.secondary.EventEntityDTO;
import com.example.emsbackend.entity.secondary.EventEntity;
import com.example.emsbackend.entity.secondary.EventParameterEntity;
import com.example.emsbackend.entity.secondary.ParameterEntity;
import com.example.emsbackend.repository.secondary.EventEntityRepository;
import com.example.emsbackend.repository.secondary.EventParameterId;
import com.example.emsbackend.repository.secondary.EventParameterMappingRepository;
import com.example.emsbackend.repository.secondary.ParameterEntityRepository;
import com.example.emsbackend.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EventEntityRepository eventEntityRepository;

    @Autowired
    private EventParameterMappingRepository eventParameterMappingRepository;

    @Autowired
    private ParameterEntityRepository parameterEntityRepository;


    /**
     * Get the parameters of an event, including its extended parameter and the public parameters
     * @param eventID
     * @return
     */
    @Override
    public Map<String, String> getEventParameters(String eventID) {
        Map<String, String> res = new HashMap<>();

        List<String> strings = getEventParameterIds(eventID);

        List<ParameterEntity> parameterEntities = strings.stream().map(elem -> parameterEntityRepository.findParameterEntityByIdentifierCode(elem)).toList();

        for (ParameterEntity parameterEntity: parameterEntities) {
            res.put(parameterEntity.getParameterName(), parameterEntity.getParameterValue());
        }

        return res;
    }

    @Override
    public List<EventEntityDTO> getAllEvents() {
        return eventEntityRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    private List<String> getEventParameterIds(String eventID) {
        return eventParameterMappingRepository.findAllParameterEntitiesForEventById(eventID);
    }

    @Transactional(readOnly = true)
    public Optional<EventEntityDTO> getEventById(String eventId) {
        EventEntity eventEntity = eventEntityRepository.findEventEntityByIdentifierCode(eventId);
        if (eventEntity == null) {
            return Optional.empty();
        }
        return Optional.of(convertEntityToDTO(eventEntity));
    }


    /**
     * Create an event, with a list of parameters id specified, assuming that the parameter has already been created
     * @param eventEntityDTO the eventObj from the frontend
     * @param parameterIds the list of parameter_ids from frontend
     * @return
     */
    @Transactional
    @Override
    public void createEvent(EventEntityDTO eventEntityDTO, List<String> parameterIds) {

        EventEntity eventEntity = convertDTOToEntity(eventEntityDTO);
        eventEntityRepository.save(eventEntity);
        String identifierCode = eventEntity.getIdentifierCode();
        for (String parameterId: parameterIds) {
            EventParameterId epeId = new EventParameterId(identifierCode, parameterId);
            EventParameterEntity epe = new EventParameterEntity(epeId);
            eventParameterMappingRepository.save(epe);
        }
    }

    @Transactional
    @Override
    public void updateEvent(EventEntityDTO newEventEntityDTO, List<String> parameterIds) {

        String eventId = newEventEntityDTO.getIdentifierCode();
        List<String> parametersOri = getEventParameterIds(eventId);
        List<String> deletedParameterIds = parametersOri.stream().filter(elem -> !parameterIds.contains(elem)).toList();
        List<String> addedParameterIds = parameterIds.stream().filter(elem -> !parametersOri.contains(elem)).toList();

        eventParameterMappingRepository.deleteAllByListOfParameters(eventId, deletedParameterIds);
        try {
            addedParameterIds.forEach(elem -> {
                eventParameterMappingRepository.insertAllByListOfParameters(eventId, elem);
            });
        } catch (Exception e) {
            throw new RuntimeException("Insertion Error");
        }
    }

    @Transactional
    @Override
    public void deleteEventById(String eventId) {
        eventEntityRepository.deleteEventEntitiesByIdentifierCode(eventId);
    }



    @Override
    public EventEntityDTO convertEntityToDTO(EventEntity inputObj) {

        Map<String, String> parameters = getEventParameters(inputObj.getIdentifierCode());
        EventEntityDTO entityDTO = modelMapper.map(inputObj, EventEntityDTO.class);
        entityDTO.setParameters(parameters);

        return entityDTO;
    }

    @Override
    public EventEntity convertDTOToEntity(EventEntityDTO inputObj) {
        // By default, it will skip the field that exists in source but doesn't exist in destination
        return modelMapper.map(inputObj, EventEntity.class);
    }
}

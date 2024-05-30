package com.example.emsbackend.service.impl;

import com.example.emsbackend.dto.secondary.EventEntityDTO;
import com.example.emsbackend.dto.secondary.ParameterEntityDTO;
import com.example.emsbackend.entity.secondary.EventEntity;
import com.example.emsbackend.entity.secondary.ParameterEntity;
import com.example.emsbackend.repository.secondary.EventEntityRepository;
import com.example.emsbackend.repository.secondary.EventParameterMappingRepository;
import com.example.emsbackend.repository.secondary.ParameterEntityRepository;
import com.example.emsbackend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class EventServiceImpl implements EventService {

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

        List<String> strings = eventParameterMappingRepository.findAllParameterEntitiesForEventById(eventID);

        List<ParameterEntity> parameterEntities = strings.stream().map(elem -> parameterEntityRepository.findParameterEntitiesByIdentifier_code(elem)).toList();

        for (ParameterEntity parameterEntity: parameterEntities) {
            res.put(parameterEntity.getParameterName(), parameterEntity.getParameterValue());
        }

        return res;
    }


    public EventEntityDTO getEventById(String eventId) {
        EventEntity eventEntity = eventEntityRepository.findEventEntityByIdentifierCode(eventId);
        Map<String, String> eventParameters = getEventParameters(eventId);




    }

    @Override
    public EventEntityDTO createEvent(EventEntityDTO eventEntityDTO) {
        return null;
    }

    @Override
    public EventEntityDTO updateEvent(EventEntityDTO newEventEntityDTO) {
        return null;
    }

    @Override
    public EventEntityDTO deleteEventById(String eventId) {
        return null;
    }


    public EventEntityDTO convertEntityToDTO(EventEntity inputObj) {
        Map<String, String> parameters = getEventParameters(inputObj.getIdentifierCode());
        return null;
    }

    public EventEntity convertDTOToEntity(EventEntityDTO inputObj) {
        return null;
    }
}

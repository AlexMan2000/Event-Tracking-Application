package com.example.emsbackend.service.events.impl;

import com.example.emsbackend.commons.enums.StatusCode;
import com.example.emsbackend.commons.status.Message;
import com.example.emsbackend.criteria_utils.searching.impl.EventEntitySearchImpl;
import com.example.emsbackend.dto.events.EventEntityDTO;
import com.example.emsbackend.dto.events.ParameterEntityDTO;
import com.example.emsbackend.entity.events.EventEntity;
import com.example.emsbackend.entity.events.EventParameterEntity;
import com.example.emsbackend.entity.events.ParameterEntity;
import com.example.emsbackend.repository.events.EventEntityRepository;
import com.example.emsbackend.repository.events.EventParameterId;
import com.example.emsbackend.repository.events.EventParameterMappingRepository;
import com.example.emsbackend.repository.events.ParameterEntityRepository;
import com.example.emsbackend.service.events.EventService;
import com.example.emsbackend.criteria_utils.searching.impl.EventEntitySearchCriteria;
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


    @Autowired
    private EventEntitySearchImpl eventEntitySearch;

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
    public List<ParameterEntityDTO> getEventParametersDTO(String eventID) {
        List<String> strings = getEventParameterIds(eventID);

        List<ParameterEntity> parameterEntities = strings.stream().map(elem -> parameterEntityRepository.findParameterEntityByIdentifierCode(elem)).toList();

        return parameterEntities.stream().map(elem -> this.modelMapper.map(elem, ParameterEntityDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<EventEntityDTO> getAllEvents() {


        return eventEntityRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());

//        return eventEntityRepository.findAllEvents().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }


    /**
     * Get all the events without parameter information, just for display purposes
     * @param searchCriteria The user specified parametrized SQL query with WHERE clause
     * @return
     */
    @Override
    public List<EventEntityDTO> getAllEventsByFilters(EventEntitySearchCriteria searchCriteria) {
        return eventEntitySearch.getItemsFiltered(searchCriteria, EventEntity.class).stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }


    /**
     * Get the parameter ids for the event identified by "eventID"
     * @param eventID
     * @return A list of parameter ids
     */
    private List<String> getEventParameterIds(String eventID) {
        return eventParameterMappingRepository.findAllParameterEntitiesForEventById(eventID);
    }

    @Transactional(readOnly = true)
    public Optional<EventEntityDTO> getEventById(String eventId) {
        EventEntity eventEntity = eventEntityRepository.findEventEntityByIdentifierCode(eventId);
        if (eventEntity == null) {
            return Optional.empty();
        }
        EventEntityDTO dtoObj = convertEntityToDTO(eventEntity);
        dtoObj.setParameterIds(getEventParameterIds(eventId));
        dtoObj.setParameterObjs(getEventParametersDTO(eventId));
        return Optional.of(dtoObj);
    }


    /**
     * Create an event, with a list of parameters id specified, assuming that the parameter has already been created
     * @param eventEntityDTO the eventObj from the frontend
     * @return
     */
    @Transactional
    @Override
    public Message createEvent(EventEntityDTO eventEntityDTO) {
        List<String> parameterIds = eventEntityDTO.getParameterIds();
        EventEntity eventEntity = convertDTOToEntity(eventEntityDTO);
        try {
            eventEntityRepository.save(eventEntity);
            String identifierCode = eventEntity.getIdentifierCode();
            for (String parameterId: parameterIds) {
                EventParameterId epeId = new EventParameterId(identifierCode, parameterId);
                EventParameterEntity epe = new EventParameterEntity(epeId);

                System.out.println(epe);
                eventParameterMappingRepository.save(epe);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(StatusCode.CREATE_FAILURE, "Error Inserting records");
        }

        return new Message(StatusCode.OK, "Ok");
    }

    @Transactional
    @Override
    public Message updateEvent(EventEntityDTO newEventEntityDTO) {

        String eventId = newEventEntityDTO.getIdentifierCode();
        List<String> parameterIdsBefore = getEventParameterIds(eventId);
        List<String> parameterIdsAfter = newEventEntityDTO.getParameterIds();
        List<String> deletedParameterIds = parameterIdsBefore.stream().filter(elem -> !parameterIdsAfter.contains(elem)).toList();
        List<String> addedParameterIds = parameterIdsAfter.stream().filter(elem -> !parameterIdsBefore.contains(elem)).toList();
        eventParameterMappingRepository.deleteAllByListOfParameters(eventId, deletedParameterIds);
        try {
            eventEntityRepository.save(convertDTOToEntity(newEventEntityDTO));
            addedParameterIds.forEach(elem -> {
                eventParameterMappingRepository.insertAllByListOfParameters(eventId, elem);
            });
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(StatusCode.UPDATE_FAILURE, "Insertion Error");
        }
        return new Message(StatusCode.OK, "Insertion Successful");

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

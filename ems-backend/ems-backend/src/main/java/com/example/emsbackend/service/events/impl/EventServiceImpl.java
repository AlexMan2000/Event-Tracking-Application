package com.example.emsbackend.service.events.impl;

import com.example.emsbackend.commons.enums.StatusCode;
import com.example.emsbackend.commons.status.Message;
import com.example.emsbackend.criteria_utils.searching.impl.EventEntitySearchImpl;
import com.example.emsbackend.dto.events.entityDTO.EventEntityDTO;
import com.example.emsbackend.dto.events.entityDTO.ParameterEntityDTO;
import com.example.emsbackend.entity.events.entityEntity.EventEntity;
import com.example.emsbackend.entity.events.mappingEntity.EventParameterEntity;
import com.example.emsbackend.entity.events.entityEntity.ParameterEntity;
import com.example.emsbackend.repository.events.entityRepository.EventEntityRepository;
import com.example.emsbackend.repository.events.compositeId.EventParameterId;
import com.example.emsbackend.repository.events.mappingRepository.EventParameterMappingRepository;
import com.example.emsbackend.repository.events.entityRepository.ParameterEntityRepository;
import com.example.emsbackend.service.events.EventService;
import com.example.emsbackend.criteria_utils.searching.EventEntitySearchCriteria;
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
    public Map<String, String> getEventParameters(Long eventID) {
        Map<String, String> res = new HashMap<>();

        List<Long> strings = getEventParameterIds(eventID);

        List<ParameterEntity> parameterEntities = strings.stream()
                .map(elem -> parameterEntityRepository.findById(elem))
                .map(elem -> elem.orElse(null))
                .filter(Objects::nonNull)
                .toList();

        for (ParameterEntity parameterEntity: parameterEntities) {
            res.put(parameterEntity.getParameterName(), parameterEntity.getParameterValue());
        }

        return res;
    }

    @Override
    public List<ParameterEntityDTO> getEventParametersDTO(Long eventID) {
        List<Long> strings = getEventParameterIds(eventID);


        List<ParameterEntity> parameterEntities = strings.stream()
                .map(elem -> parameterEntityRepository.findById(elem))
                .map(elem -> elem.orElse(null))
                .filter(Objects::nonNull)
                .toList();

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
    private List<Long> getEventParameterIds(Long eventID) {
        return eventParameterMappingRepository.findAllParameterEntitiesForEventById(eventID);
    }

    @Transactional(readOnly = true)
    public Optional<EventEntityDTO> getEventById(Long eventId) {
        Optional<EventEntity> eventEntity = eventEntityRepository.findById(eventId);
        if (eventEntity.isEmpty()) {
            return Optional.empty();
        }
        EventEntityDTO dtoObj = convertEntityToDTO(eventEntity.get());
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

        List<Long> parameterIds = eventEntityDTO.getParameterIds();
        EventEntity eventEntity = convertDTOToEntity(eventEntityDTO);
        try {
            eventEntityRepository.save(eventEntity);
            Long eventId = eventEntity.getId();
            for (Long parameterId: parameterIds) {
                EventParameterId epeId = new EventParameterId(eventId, parameterId);
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

        Long eventId = newEventEntityDTO.getId();
        List<Long> parameterIdsBefore = getEventParameterIds(eventId);
        List<Long> parameterIdsAfter = newEventEntityDTO.getParameterIds();
        List<Long> deletedParameterIds = parameterIdsBefore.stream().filter(elem -> !parameterIdsAfter.contains(elem)).toList();
        List<Long> addedParameterIds = parameterIdsAfter.stream().filter(elem -> !parameterIdsBefore.contains(elem)).toList();
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

        Map<String, String> parameters = getEventParameters(inputObj.getId());
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

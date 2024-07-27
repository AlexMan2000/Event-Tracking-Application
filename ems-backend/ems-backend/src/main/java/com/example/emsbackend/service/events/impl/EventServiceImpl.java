package com.example.emsbackend.service.events.impl;

import com.example.emsbackend.commons.enums.StatusCode;
import com.example.emsbackend.commons.status.Message;
import com.example.emsbackend.criteria_utils.searching.impl.EventEntitySearchImpl;
import com.example.emsbackend.dto.events.getDTO.EventEntityGetObjectDTO;
import com.example.emsbackend.dto.events.getDTO.GetIdentifiersDTO;
import com.example.emsbackend.dto.events.modifyDTO.EventEntityUpdateObjectDTO;
import com.example.emsbackend.entity.events.entityEntity.EventEntity;
import com.example.emsbackend.repository.events.entityRepository.EventEntityRepository;
import com.example.emsbackend.service.events.EventService;
import com.example.emsbackend.criteria_utils.searching.EventEntitySearchCriteria;
import com.example.emsbackend.service.utils.UtilityMethods;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EventEntityRepository eventEntityRepository;

    @Autowired
    private UtilityMethods utilityMethods;

    @Autowired
    private EventEntitySearchImpl eventEntitySearch;


    @Override
    public List<EventEntityGetObjectDTO> getAllEvents() {
        return eventEntityRepository.findAll().stream()
                .map(elem -> this.modelMapper.map(elem, EventEntityGetObjectDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EventEntityGetObjectDTO> getAllEventsFiltered(EventEntitySearchCriteria searchCriteria) {
        return eventEntitySearch.getItemsFiltered(searchCriteria, EventEntity.class)
                .stream().map(elem -> this.modelMapper.map(elem, EventEntityGetObjectDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GetIdentifiersDTO> getAllMetaData() {
        return eventEntityRepository.findAllMetaData().stream()
                .map(elem -> this.modelMapper.map(elem, GetIdentifiersDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EventEntityGetObjectDTO getEventById(Long eventId) {
        return eventEntityRepository.findById(eventId)
                .map(elem -> this.modelMapper.map(elem, EventEntityGetObjectDTO.class))
                .orElseThrow(() -> {throw new RuntimeException("Event with" + eventId + "not found!");});
    }

    @Override
    public Message createEvent(EventEntityUpdateObjectDTO eventEntityUpdateObjectDTO) {

        try {
            EventEntity eventEntity = this.utilityMethods.recoverEntityFromUpdateDTO(
                    EventEntityUpdateObjectDTO.class
                    , EventEntity.class
                    , "event"
                    , eventEntityUpdateObjectDTO
                    , 0
                    , List.of("parameter")
            );
            eventEntity.setTriggerTimes(0L);
            eventEntityRepository.save(eventEntity);
        }catch (Exception e) {
            e.printStackTrace();
            return new Message(StatusCode.CREATE_FAILURE, "Error Creating records");
        }
        return new Message(StatusCode.OK, "Successfully creating records");
    }

    @Override
    public Message updateEvent(EventEntityUpdateObjectDTO eventEntityUpdateObjectDTO) {
        try {
            EventEntity eventEntity = this.utilityMethods.recoverEntityFromUpdateDTO(
                    EventEntityUpdateObjectDTO.class
                    , EventEntity.class
                    , "event"
                    , eventEntityUpdateObjectDTO
                    , 1
                    , List.of("parameter")
            );
            eventEntityRepository.save(eventEntity);
        }catch (Exception e) {
            e.printStackTrace();
            return new Message(StatusCode.CREATE_FAILURE, "Error inserting records.");
        }
        return new Message(StatusCode.OK, "Successfully inserting records.");
    }

}

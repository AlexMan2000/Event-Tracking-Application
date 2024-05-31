package com.example.emsbackend.service;

import com.example.emsbackend.dto.secondary.EventEntityDTO;
import com.example.emsbackend.dto.secondary.ParameterEntityDTO;
import com.example.emsbackend.entity.secondary.EventEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EventService {


    // Get a list of parameters to a given event
    Map<String, String> getEventParameters(String eventID);


    void createEvent(EventEntityDTO eventEntityDTO, List<String> parameterIds);

    Optional<EventEntityDTO> getEventById(String eventId);
    /**
     * Manage the event, primarily the event status(in use, idle, deleted, completed)
     * @param newEventEntityDTO
     * @return
     */
    void updateEvent(EventEntityDTO newEventEntityDTO, List<String> parameterIds);


    void deleteEventById(String eventId);

    public EventEntityDTO convertEntityToDTO(EventEntity inputObj);
    public EventEntity convertDTOToEntity(EventEntityDTO inputObj);

}

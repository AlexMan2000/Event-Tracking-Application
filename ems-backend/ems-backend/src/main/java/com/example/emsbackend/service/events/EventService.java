package com.example.emsbackend.service.events;

import com.example.emsbackend.commons.status.Message;
import com.example.emsbackend.dto.events.EventEntityDTO;
import com.example.emsbackend.dto.events.ParameterEntityDTO;
import com.example.emsbackend.entity.events.EventEntity;
import com.example.emsbackend.criteria_utils.searching.impl.EventEntitySearchCriteria;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EventService {


    // Get a list of parameters to a given event
    Map<String, String> getEventParameters(String eventID);


    List<ParameterEntityDTO> getEventParametersDTO(String eventID);


    List<EventEntityDTO> getAllEvents();


    List<EventEntityDTO> getAllEventsByFilters(EventEntitySearchCriteria searchCriteria);

    Message createEvent(EventEntityDTO eventEntityDTO);

    Optional<EventEntityDTO> getEventById(String eventId);
    /**
     * Manage the event, primarily the event status(in use, idle, deleted, completed)
     * @param newEventEntityDTO
     * @return
     */
    Message updateEvent(EventEntityDTO newEventEntityDTO);

    public EventEntityDTO convertEntityToDTO(EventEntity inputObj);
    public EventEntity convertDTOToEntity(EventEntityDTO inputObj);

}

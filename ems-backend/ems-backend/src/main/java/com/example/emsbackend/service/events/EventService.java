package com.example.emsbackend.service.events;

import com.example.emsbackend.commons.status.Message;
import com.example.emsbackend.dto.events.entityDTO.EventEntityDTO;
import com.example.emsbackend.dto.events.entityDTO.ParameterEntityDTO;
import com.example.emsbackend.entity.events.entityEntity.EventEntity;
import com.example.emsbackend.criteria_utils.searching.EventEntitySearchCriteria;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EventService {


    // Get a list of parameters to a given event
    Map<String, String> getEventParameters(Long eventID);


    List<ParameterEntityDTO> getEventParametersDTO(Long eventID);


    List<EventEntityDTO> getAllEvents();


    List<EventEntityDTO> getAllEventsByFilters(EventEntitySearchCriteria searchCriteria);

    Message createEvent(EventEntityDTO eventEntityDTO);

    Optional<EventEntityDTO> getEventById(Long eventId);
    /**
     * Manage the event, primarily the event status(in use, idle, deleted, completed)
     * @param newEventEntityDTO
     * @return
     */
    Message updateEvent(EventEntityDTO newEventEntityDTO);

    EventEntityDTO convertEntityToDTO(EventEntity inputObj);
    EventEntity convertDTOToEntity(EventEntityDTO inputObj);

}

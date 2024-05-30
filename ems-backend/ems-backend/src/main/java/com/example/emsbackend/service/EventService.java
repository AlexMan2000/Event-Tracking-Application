package com.example.emsbackend.service;

import com.example.emsbackend.dto.secondary.EventEntityDTO;

import java.util.List;
import java.util.Map;

public interface EventService {


    // Get a list of parameters to a given event
    Map<String, String> getEventParameters(String eventID);



    EventEntityDTO createEvent(EventEntityDTO eventEntityDTO);


    /**
     * Manage the event, primarily the event status(in use, idle, deleted, completed)
     * @param newEventEntityDTO
     * @return
     */
    EventEntityDTO updateEvent(EventEntityDTO newEventEntityDTO);


    EventEntityDTO deleteEventById(String eventId);



}

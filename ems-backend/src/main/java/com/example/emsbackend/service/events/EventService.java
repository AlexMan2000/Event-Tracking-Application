package com.example.emsbackend.service.events;

import com.example.emsbackend.commons.status.Message;
import com.example.emsbackend.dto.events.getDTO.EventEntityGetObjectDTO;
import com.example.emsbackend.dto.events.getDTO.GetIdentifiersDTO;
import com.example.emsbackend.dto.events.modifyDTO.EventEntityUpdateObjectDTO;
import com.example.emsbackend.criteria_utils.searching.EventEntitySearchCriteria;

import java.util.List;


public interface EventService {


    List<EventEntityGetObjectDTO> getAllEvents();

    List<EventEntityGetObjectDTO> getAllEventsFiltered(EventEntitySearchCriteria searchCriteria);

    List<GetIdentifiersDTO> getAllMetaData();

    EventEntityGetObjectDTO getEventById(Long eventId);

    Message createEvent(EventEntityUpdateObjectDTO eventEntityUpdateObjectDTO);

    Message updateEvent(EventEntityUpdateObjectDTO eventEntityUpdateObjectDTO);


}

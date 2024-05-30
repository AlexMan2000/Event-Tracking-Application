package com.example.emsbackend.service;

import com.example.emsbackend.dto.secondary.EventEntityDTO;

import java.util.List;

public interface PageService {


    /**
     * Get all the events with (eventType) of the page specified by pageID
     * @param pageID
     * @return
     */
    List<EventEntityDTO> getEventByTypeOfPageById(String pageID, String eventType);


    /**
     * Get all the events with (eventStatus) of the page specified by pageID
     * @param pageID
     * @return
     */
    List<EventEntityDTO> getEventByStatusOfPageById(String pageID, String eventStatus);

}

package com.example.emsbackend.service.events;

import com.example.emsbackend.dto.events.EventEntityDTO;
import com.example.emsbackend.dto.events.PageEntityDTO;
import com.example.emsbackend.dto.events.ProjectEntityDTO;
import com.example.emsbackend.entity.events.PageEntity;

import java.util.List;

public interface PageService {

    PageEntityDTO getPageEntityDTOById(String pageID);
    void createOrUpdatePage(PageEntityDTO pageEntityDTO);
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


    /**
     * Get all the projects that contain the page with pageID
     * @param pageID
     * @return
     */
    List<ProjectEntityDTO> getProjectEntityForPageByPageId(String pageID);


    PageEntityDTO convertEntityToDTO(PageEntity inputObj);


    PageEntity convertDTOToEntity(PageEntityDTO inputObj);


}

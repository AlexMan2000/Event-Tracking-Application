package com.example.emsbackend.service;

import com.example.emsbackend.dto.secondary.EventEntityDTO;
import com.example.emsbackend.dto.secondary.PageEntityDTO;
import com.example.emsbackend.dto.secondary.ProjectEntityDTO;
import com.example.emsbackend.entity.secondary.PageEntity;

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

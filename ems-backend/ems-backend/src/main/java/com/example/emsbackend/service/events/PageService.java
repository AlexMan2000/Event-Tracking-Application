package com.example.emsbackend.service.events;

import com.example.emsbackend.dto.events.entityDTO.EventEntityDTO;
import com.example.emsbackend.dto.events.entityDTO.PageEntityDTO;
import com.example.emsbackend.dto.events.getDTO.ProjectEntityGetObjectDTO;
import com.example.emsbackend.entity.events.entityEntity.PageEntity;

import java.util.List;

public interface PageService {

    PageEntityDTO getPageEntityDTOById(Long pageID);
    void createOrUpdatePage(PageEntityDTO pageEntityDTO);
    /**
     * Get all the events with (eventType) of the page specified by pageID
     * @param pageID
     * @return
     */
    List<EventEntityDTO> getEventByTypeOfPageById(Long pageID, String eventType);


    /**
     * Get all the events with (eventStatus) of the page specified by pageID
     * @param pageID
     * @return
     */
    List<EventEntityDTO> getEventByStatusOfPageById(Long pageID, String eventStatus);


    /**
     * Get all the projects that contain the page with pageID
     * @param pageID
     * @return
     */
    List<ProjectEntityGetObjectDTO> getProjectEntityForPageByPageId(Long pageID);


    PageEntityDTO convertEntityToDTO(PageEntity inputObj);


    PageEntity convertDTOToEntity(PageEntityDTO inputObj);


}

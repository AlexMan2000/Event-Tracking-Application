package com.example.emsbackend.service.impl;

import com.example.emsbackend.dto.secondary.EventEntityDTO;
import com.example.emsbackend.dto.secondary.PageEntityDTO;
import com.example.emsbackend.entity.secondary.PageEntity;
import com.example.emsbackend.service.PageService;

import java.util.List;

public class PageServiceImpl implements PageService {
    @Override
    public List<EventEntityDTO> getEventByTypeOfPageById(String pageID, String eventType) {
        return null;
    }

    @Override
    public List<EventEntityDTO> getEventByStatusOfPageById(String pageID, String eventStatus) {
        return null;
    }


    public PageEntityDTO convertEntityToDTO(PageEntity inputObj) {
        return null;
    }


    public PageEntity convertDTOToEntity(PageEntityDTO inputObj) {
        return null;
    }
}

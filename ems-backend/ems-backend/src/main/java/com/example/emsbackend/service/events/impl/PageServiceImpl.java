package com.example.emsbackend.service.events.impl;

import com.example.emsbackend.dto.events.EventEntityDTO;
import com.example.emsbackend.dto.events.PageEntityDTO;
import com.example.emsbackend.dto.events.ProjectEntityDTO;
import com.example.emsbackend.entity.events.PageEntity;
import com.example.emsbackend.repository.events.EventPageMappingRepository;
import com.example.emsbackend.repository.events.PageEntityRepository;
import com.example.emsbackend.repository.events.ProjectPageMappingRepository;
import com.example.emsbackend.service.events.EventService;
import com.example.emsbackend.service.events.PageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private PageEntityRepository pageEntityRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventPageMappingRepository eventPageMappingRepository;

    @Autowired
    private ProjectPageMappingRepository projectPageMappingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public PageEntityDTO getPageEntityDTOById(String pageID) {
        return convertEntityToDTO(pageEntityRepository.findPageEntitiesByIdentifierCode(pageID));
    }

    @Transactional
    @Override
    public void createOrUpdatePage(PageEntityDTO pageEntityDTO) {
        pageEntityRepository.save(convertDTOToEntity(pageEntityDTO));
    }

    @Transactional(readOnly = true)
    @Override
    public List<EventEntityDTO> getEventByTypeOfPageById(String pageID, String eventType) {
        return eventPageMappingRepository.getEventsForPageByPageId(pageID)
                .stream()
                .map(elem -> eventService.convertEntityToDTO(elem))
                .filter(elem -> elem.getEventType().equals(eventType))
                .toList();
    }


    @Transactional(readOnly = true)
    @Override
    public List<EventEntityDTO> getEventByStatusOfPageById(String pageID, String eventStatus) {
        return eventPageMappingRepository.getEventsForPageByPageId(pageID)
                .stream()
                .map(elem -> eventService.convertEntityToDTO(elem))
                .filter(elem -> elem.getEventType().equals(eventStatus))
                .toList();
    }


    @Transactional(readOnly = true)
    @Override
    public List<ProjectEntityDTO> getProjectEntityForPageByPageId(String pageID) {
        return projectPageMappingRepository.getAllProjectsWithPageByID(pageID).stream()
                .map(elem -> this.modelMapper.map(elem, ProjectEntityDTO.class))
                .toList();
    }


    public PageEntityDTO convertEntityToDTO(PageEntity inputObj) {
        return modelMapper.map(inputObj, PageEntityDTO.class);
    }


    public PageEntity convertDTOToEntity(PageEntityDTO inputObj) {
        return modelMapper.map(inputObj, PageEntity.class);
    }
}

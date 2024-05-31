package com.example.emsbackend.service.impl;

import com.example.emsbackend.dto.secondary.EventEntityDTO;
import com.example.emsbackend.dto.secondary.PageEntityDTO;
import com.example.emsbackend.dto.secondary.ProjectEntityDTO;
import com.example.emsbackend.entity.secondary.EventEntity;
import com.example.emsbackend.entity.secondary.PageEntity;
import com.example.emsbackend.repository.secondary.EventPageMappingRepository;
import com.example.emsbackend.repository.secondary.PageEntityRepository;
import com.example.emsbackend.repository.secondary.ProjectPageMappingRepository;
import com.example.emsbackend.service.EventService;
import com.example.emsbackend.service.PageService;
import com.example.emsbackend.service.ProjectService;
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
    private ProjectService projectService;

    @Autowired
    private PageService pageService;
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
                .map(elem -> projectService.convertEntityToDTO(elem))
                .toList();
    }


    public PageEntityDTO convertEntityToDTO(PageEntity inputObj) {
        return modelMapper.map(inputObj, PageEntityDTO.class);
    }


    public PageEntity convertDTOToEntity(PageEntityDTO inputObj) {
        return modelMapper.map(inputObj, PageEntity.class);
    }
}

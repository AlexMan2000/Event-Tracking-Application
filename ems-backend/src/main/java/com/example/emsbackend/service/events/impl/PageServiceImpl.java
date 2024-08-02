package com.example.emsbackend.service.events.impl;

import com.example.emsbackend.commons.enums.https.StatusCode;
import com.example.emsbackend.commons.status.Message;
import com.example.emsbackend.criteria_utils.searching.PageEntitySearchCriteria;
import com.example.emsbackend.criteria_utils.searching.impl.PageEntitySearchImpl;
import com.example.emsbackend.dto.events.getDTO.GetIdentifiersDTO;
import com.example.emsbackend.dto.events.getDTO.PageEntityGetObjectDTO;
import com.example.emsbackend.dto.events.modifyDTO.PageEntityUpdateObjectDTO;
import com.example.emsbackend.entity.events.entityEntity.PageEntity;
import com.example.emsbackend.repository.events.entityRepository.PageEntityRepository;
import com.example.emsbackend.service.events.PageService;

import com.example.emsbackend.service.utils.UtilityMethods;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UtilityMethods utilityMethods;

    @Autowired
    private PageEntitySearchImpl pageEntitySearchCriteria;

    @Autowired
    private PageEntityRepository pageEntityRepository;

    @Override
    public List<PageEntityGetObjectDTO> getAllPages() {
        return pageEntityRepository.findAll().stream()
                .map(elem -> this.modelMapper.map(elem, PageEntityGetObjectDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PageEntityGetObjectDTO> getAllPagesFiltered(PageEntitySearchCriteria searchCriteria) {
        return pageEntitySearchCriteria.getItemsFiltered(searchCriteria, PageEntity.class)
                .stream().map(elem -> this.modelMapper.map(elem, PageEntityGetObjectDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GetIdentifiersDTO> getAllMetaData() {
        return pageEntityRepository.findAllMetaData().stream()
                .map(elem -> this.modelMapper.map(elem, GetIdentifiersDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PageEntityGetObjectDTO getPageById(Long pageId) {
        return pageEntityRepository.findById(pageId)
                .map(elem -> this.modelMapper.map(elem, PageEntityGetObjectDTO.class))
                .orElseThrow(() -> {throw new RuntimeException("Page with" + pageId + "not found!");});
    }

    @Override
    public Message createPage(PageEntityUpdateObjectDTO pageEntityUpdateObjectDTO) {
        try {
            PageEntity pageEntity = this.utilityMethods.recoverEntityFromUpdateDTO(
                    PageEntityUpdateObjectDTO.class
                    , PageEntity.class
                    , "page"
                    , pageEntityUpdateObjectDTO
                    , 0
                    , List.of("parameter")
            );
            pageEntityRepository.save(pageEntity);
        }catch (Exception e) {
            e.printStackTrace();
            return new Message(StatusCode.CREATE_FAILURE, "Error Creating records");
        }
        return new Message(StatusCode.OK, "Successfully creating records");
    }

    @Override
    public Message updatePage(PageEntityUpdateObjectDTO pageEntityUpdateObjectDTO) {
        try {
            PageEntity pageEntity = this.utilityMethods.recoverEntityFromUpdateDTO(
                    PageEntityUpdateObjectDTO.class
                    , PageEntity.class
                    , "page"
                    , pageEntityUpdateObjectDTO
                    , 1
                    , List.of("parameter", "event")
            );
            pageEntityRepository.save(pageEntity);
        }catch (Exception e) {
            e.printStackTrace();
            return new Message(StatusCode.CREATE_FAILURE, "Error inserting records");
        }
        return new Message(StatusCode.OK, "Successfully inserting records");
    }
}

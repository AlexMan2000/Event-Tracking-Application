package com.example.emsbackend.service.events;


import com.example.emsbackend.commons.status.Message;
import com.example.emsbackend.criteria_utils.searching.EventEntitySearchCriteria;
import com.example.emsbackend.criteria_utils.searching.PageEntitySearchCriteria;
import com.example.emsbackend.dto.events.getDTO.EventEntityGetObjectDTO;
import com.example.emsbackend.dto.events.getDTO.GetIdentifiersDTO;
import com.example.emsbackend.dto.events.getDTO.PageEntityGetObjectDTO;
import com.example.emsbackend.dto.events.modifyDTO.EventEntityUpdateObjectDTO;
import com.example.emsbackend.dto.events.modifyDTO.PageEntityUpdateObjectDTO;

import java.util.List;

public interface PageService {

    List<PageEntityGetObjectDTO> getAllPages();

    List<PageEntityGetObjectDTO> getAllPagesFiltered(PageEntitySearchCriteria searchCriteria);

    List<GetIdentifiersDTO> getAllMetaData();

    PageEntityGetObjectDTO getPageById(Long pageId);

    Message createPage(PageEntityUpdateObjectDTO pageEntityUpdateObjectDTO);

    Message updatePage(PageEntityUpdateObjectDTO pageEntityUpdateObjectDTO);


}

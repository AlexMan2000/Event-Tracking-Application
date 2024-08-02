package com.example.emsbackend.controller.events;


import com.example.emsbackend.commons.enums.https.StatusCode;
import com.example.emsbackend.commons.status.Message;
import com.example.emsbackend.criteria_utils.searching.PageEntitySearchCriteria;
import com.example.emsbackend.dto.events.getDTO.GetIdentifiersDTO;
import com.example.emsbackend.dto.events.getDTO.PageEntityGetObjectDTO;
import com.example.emsbackend.dto.events.modifyDTO.PageEntityUpdateObjectDTO;
import com.example.emsbackend.service.events.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pages")
public class PageController {
    @Autowired
    private PageService pageService;


    @GetMapping("/meta")
    public List<GetIdentifiersDTO> getAllMetadata() {
        return pageService.getAllMetaData();
    }



    @GetMapping("/all")
    public List<PageEntityGetObjectDTO> getAllProjects() {
        return pageService.getAllPages();
    }


    @GetMapping("/allfiltered")
    public List<PageEntityGetObjectDTO> getAllProjectsFiltered(PageEntitySearchCriteria searchCriteria) {
        return pageService.getAllPagesFiltered(searchCriteria);
    }

    @GetMapping("/{id}")
    public PageEntityGetObjectDTO getProjectById(@PathVariable Long id) {
        return pageService.getPageById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEvent(@RequestBody PageEntityUpdateObjectDTO pageEntityUpdateObjectDTO) {
        Message message = pageService.createPage(pageEntityUpdateObjectDTO);
        if (message.getStatusCode() == StatusCode.OK) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEvent(@RequestBody PageEntityUpdateObjectDTO pageEntityUpdateObjectDTO) {
        Message message = pageService.updatePage(pageEntityUpdateObjectDTO);
        if (message.getStatusCode() == StatusCode.OK) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }
}

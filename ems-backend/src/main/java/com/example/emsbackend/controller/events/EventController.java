package com.example.emsbackend.controller.events;


import com.example.emsbackend.commons.enums.https.StatusCode;
import com.example.emsbackend.commons.status.Message;
import com.example.emsbackend.criteria_utils.searching.EventEntitySearchCriteria;
import com.example.emsbackend.dto.events.getDTO.EventEntityGetObjectDTO;
import com.example.emsbackend.dto.events.getDTO.GetIdentifiersDTO;
import com.example.emsbackend.dto.events.modifyDTO.EventEntityUpdateObjectDTO;
import com.example.emsbackend.service.events.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/events")
public class EventController { 

    @Autowired
    private EventService eventService;


    @GetMapping("/meta")
    public List<GetIdentifiersDTO> getAllMetadata() {
        return eventService.getAllMetaData();
    }



    @GetMapping("/all")
    public List<EventEntityGetObjectDTO> getAllProjects() {
        return eventService.getAllEvents();
    }


    @GetMapping("/allfiltered")
    public List<EventEntityGetObjectDTO> getAllProjectsFiltered(EventEntitySearchCriteria searchCriteria) {
        return eventService.getAllEventsFiltered(searchCriteria);
    }

    @GetMapping("/{id}")
    public EventEntityGetObjectDTO getProjectById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEvent(@RequestBody EventEntityUpdateObjectDTO eventEntityUpdateObjectDTO) {
        Message message = eventService.createEvent(eventEntityUpdateObjectDTO);
        if (message.getStatusCode() == StatusCode.OK) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEvent(@RequestBody EventEntityUpdateObjectDTO eventEntityUpdateObjectDTO) {
        Message message = eventService.updateEvent(eventEntityUpdateObjectDTO);
        if (message.getStatusCode() == StatusCode.OK) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }


}

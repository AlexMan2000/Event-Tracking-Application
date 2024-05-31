package com.example.emsbackend.controller;


import com.example.emsbackend.dto.secondary.EventEntityDTO;
import com.example.emsbackend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    
    @GetMapping("/{id}")
    public ResponseEntity<EventEntityDTO> getEventDTOById(@PathVariable String id) {
        Optional<EventEntityDTO> eventById = eventService.getEventById(id);
        return eventById.isEmpty() ? ResponseEntity.notFound().build(): ResponseEntity.ok(eventById.get())
    }





}

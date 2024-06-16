package com.example.emsbackend.controller;


import com.example.emsbackend.dto.secondary.EventEntityDTO;
import com.example.emsbackend.service.EventService;
import jdk.jfr.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController { 

    @Autowired
    private EventService eventService;


    @GetMapping("/{id}")
    public ResponseEntity<EventEntityDTO> getEventDTOById(@PathVariable String id) {
        Optional<EventEntityDTO> eventById = eventService.getEventById(id);
        return eventById.isEmpty() ? ResponseEntity.notFound().build(): ResponseEntity.ok(eventById.get());
    }


    /**
     * 精确搜索事件
     * @return
     */
    public ResponseEntity<List<EventEntityDTO>> getEventByFilters() {
        return null;
    }


    @GetMapping("/all")
    public ResponseEntity<List<EventEntityDTO>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }




    /**
     * 创建新的事件
     * @param eventEntityDTO
     * @return
     */
    public ResponseEntity<EventEntityDTO> createNewEvent(EventEntityDTO eventEntityDTO) {
        return null;
    }


    /**
     * 事件上线下线
     * @param eventId
     * @param eventEntityDTO
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateEventById(@PathVariable String id, EventEntityDTO eventEntityDTO) {
        return null;
    }






}

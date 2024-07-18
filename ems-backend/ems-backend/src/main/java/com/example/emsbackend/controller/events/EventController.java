package com.example.emsbackend.controller.events;


import com.example.emsbackend.commons.enums.StatusCode;
import com.example.emsbackend.commons.status.Message;
import com.example.emsbackend.criteria_utils.searching.impl.EventEntitySearchCriteria;
import com.example.emsbackend.dto.events.EventEntityDTO;
import com.example.emsbackend.service.events.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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

    @GetMapping("/allTypes")
    public ResponseEntity<List<String>> getAllEventTypes() {
        List<String> eventTypes = eventService.getAllEvents().stream().map(EventEntityDTO::getEventType).collect(Collectors.toList());
        // De-duplicate
        return ResponseEntity.ok(new ArrayList<>(new HashSet<>(eventTypes)));
    }


    @GetMapping("/allStatus")
    public ResponseEntity<List<String>> getAllEventStatus() {
        List<String> eventTypes = eventService.getAllEvents().stream().map(EventEntityDTO::getEventStatus).collect(Collectors.toList());
        // De-duplicate
        return ResponseEntity.ok(new ArrayList<>(new HashSet<>(eventTypes)));
    }

//    @GetMapping("/{eventId}-parameter")
//    public ResponseEntity<List<ParameterEntityDTO>> getParamtersDTOsOfEventById(@PathVariable String eventId) {
//        System.out.println("triggered here");
//        return ResponseEntity.ok(eventService.getEventParametersDTO(eventId));
//    }


    @GetMapping("/all")
    /**
     * 精确搜索事件
     * @return
     */
    public ResponseEntity<List<EventEntityDTO>> getEventByFilters(EventEntitySearchCriteria searchCriteria) {
        return ResponseEntity.ok(eventService.getAllEventsByFilters(searchCriteria));
    }


    @PostMapping("/add")
    public ResponseEntity<EventEntityDTO> createNewEvent(@RequestBody EventEntityDTO eventEntityDTO) {
        // Need to modify later
        initializeNewEvent(eventEntityDTO);
        Message status = eventService.createEvent(eventEntityDTO);
        if (status.getStatusCode().equals(StatusCode.CREATE_FAILURE)) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }


    private void initializeNewEvent(EventEntityDTO eventEntityDTO) {
        // Need to modify later
        eventEntityDTO.setCreator("alex");
        eventEntityDTO.setGmtCreate(new Date());
        eventEntityDTO.setGmtModify(new Date());
        eventEntityDTO.setIdentifierCode(UUID.randomUUID().toString());
        eventEntityDTO.setSampleImages(null);
        eventEntityDTO.setTriggerTimes(0L);
    }


    /**
     * 事件上线下线
     * @param newEventEntityDTO
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<String> updateEvent(@RequestBody EventEntityDTO newEventEntityDTO) {
        Optional<EventEntityDTO> originalEventObj = eventService.getEventById(newEventEntityDTO.getIdentifierCode());
        if (originalEventObj.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        EventEntityDTO originalEventEntityDTOTemp = originalEventObj.get();

        Date nowTimeStamp = new Date();
        String eventStatusBefore = originalEventEntityDTOTemp.getEventStatus();
        String eventStatusAfter = newEventEntityDTO.getEventStatus();
        if (!eventStatusBefore.equals(eventStatusAfter)) {
            if (eventStatusAfter.equals("已上线")) {
                newEventEntityDTO.setEventOnlineTime(nowTimeStamp);
            } else if (eventStatusAfter.equals("未上线") || eventStatusAfter.equals("已删除")) {
                newEventEntityDTO.setEventOfflineTime(nowTimeStamp);
            }
        }

        newEventEntityDTO.setGmtModify(nowTimeStamp);
        Message status = eventService.updateEvent(newEventEntityDTO);
        if (status.getStatusCode().equals(StatusCode.UPDATE_FAILURE)) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }






}

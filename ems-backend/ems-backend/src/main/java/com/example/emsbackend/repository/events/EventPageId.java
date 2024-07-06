package com.example.emsbackend.repository.events;


import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
public class EventPageId implements Serializable {
    @Column(name="event_id")
    private String eventId;
    @Column(name="page_id")
    private String pageId;
}

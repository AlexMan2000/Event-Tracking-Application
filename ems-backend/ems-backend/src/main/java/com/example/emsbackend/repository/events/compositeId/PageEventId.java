package com.example.emsbackend.repository.events.compositeId;


import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
public class PageEventId implements Serializable {
    @Column(name="event_id")
    private Long eventId;
    @Column(name="page_id")
    private Long pageId;
}

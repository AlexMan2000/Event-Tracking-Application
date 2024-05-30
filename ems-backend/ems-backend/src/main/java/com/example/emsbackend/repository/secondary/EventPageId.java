package com.example.emsbackend.repository.secondary;


import lombok.Data;

import java.io.Serializable;

@Data
public class EventPageId implements Serializable {
    private String eventId;
    private String pageId;
}

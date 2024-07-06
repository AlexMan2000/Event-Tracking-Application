package com.example.emsbackend.entity.events;


import com.example.emsbackend.repository.events.EventPageId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="event_page")
public class EventPageEntity {
    @EmbeddedId
    private EventPageId id;

}

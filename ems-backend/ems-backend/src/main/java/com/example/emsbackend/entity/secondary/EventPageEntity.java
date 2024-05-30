package com.example.emsbackend.entity.secondary;


import com.example.emsbackend.repository.secondary.EventPageId;
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

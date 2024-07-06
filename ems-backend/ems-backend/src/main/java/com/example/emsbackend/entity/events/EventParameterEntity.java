package com.example.emsbackend.entity.events;


import com.example.emsbackend.repository.events.EventParameterId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="EventParameterEntity")
@Table(name="event_parameter")
public class EventParameterEntity {

    @EmbeddedId
    private EventParameterId id;
}

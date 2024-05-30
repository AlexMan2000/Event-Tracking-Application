package com.example.emsbackend.entity.secondary;


import com.example.emsbackend.repository.secondary.EventParameterId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="event_parameter")
public class EventParameterEntity {

    @EmbeddedId
    private EventParameterId id;
}

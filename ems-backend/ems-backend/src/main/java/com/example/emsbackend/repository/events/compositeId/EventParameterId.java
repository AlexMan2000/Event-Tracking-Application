package com.example.emsbackend.repository.events.compositeId;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventParameterId implements Serializable {
    @Column(name="event_id")
    private Long eventId;

    @Column(name="parameter_id")
    private Long parameterId;
}

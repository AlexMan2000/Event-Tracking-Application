package com.example.emsbackend.repository.secondary;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class EventParameterId implements Serializable {
    private String eventId;
    private String parameterId;
}

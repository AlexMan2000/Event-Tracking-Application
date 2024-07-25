package com.example.emsbackend.service.events;

import com.example.emsbackend.dto.events.getDTO.GetIdentifiersDTO;

import java.util.List;

public interface ModuleService {

    List<GetIdentifiersDTO> getAllMetaData();
}

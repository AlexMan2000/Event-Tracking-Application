package com.example.emsbackend.service.impl;

import com.example.emsbackend.dto.secondary.ParameterEntityDTO;
import com.example.emsbackend.service.ParameterService;

import java.util.List;
import java.util.Map;

public class ParameterServiceImpl implements ParameterService {
    @Override
    public void createParameter(ParameterEntityDTO parameterEntityDTO) {

    }

    @Override
    public List<ParameterEntityDTO> getParameterEntityDTOsForEventById(String eventID) {
        return null;
    }

    @Override
    public Map<String, List<String>> parseParameterScope(String jsonEncodedString) {
        return null;
    }

    @Override
    public Map<String, List<String>> parseParameterValue(String jsonEncodedString) {
        return null;
    }
}

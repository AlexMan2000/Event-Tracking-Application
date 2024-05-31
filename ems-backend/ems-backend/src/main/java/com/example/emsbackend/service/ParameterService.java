package com.example.emsbackend.service;

import com.example.emsbackend.dto.secondary.EventEntityDTO;
import com.example.emsbackend.dto.secondary.ParameterEntityDTO;


import java.util.List;
import java.util.Map;

public interface ParameterService {

    void createParameter(ParameterEntityDTO parameterEntityDTO);

    default void createParameters(List<ParameterEntityDTO> parameterEntityDTOList) {
        for (ParameterEntityDTO parameterEntityDTO: parameterEntityDTOList) {
            createParameter(parameterEntityDTO);
        }
    }

    List<EventEntityDTO> getEventForParameterByPID(String eventID);


    /**
     * 验证上报数据
     * @param
     * @param
     * @return
     */
    Boolean validateParameterValue(String inputValue, String parameterID);

}

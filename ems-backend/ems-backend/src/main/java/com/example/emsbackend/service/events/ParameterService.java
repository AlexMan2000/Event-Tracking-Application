package com.example.emsbackend.service.events;

import com.example.emsbackend.criteria_utils.searching.impl.ParameterEntitySearchCriteria;
import com.example.emsbackend.dto.events.EventEntityDTO;
import com.example.emsbackend.dto.events.ParameterEntityDTO;


import java.util.List;

public interface ParameterService {

    void createParameter(ParameterEntityDTO parameterEntityDTO);


    List<String> getAllParameterIdentifiers();


    List<ParameterEntityDTO> getAllParametersFiltered(ParameterEntitySearchCriteria searchCriteria);


    ParameterEntityDTO getParameterById(String id);


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

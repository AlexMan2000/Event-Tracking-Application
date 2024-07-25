package com.example.emsbackend.service.events;

import com.example.emsbackend.criteria_utils.searching.ParameterEntitySearchCriteria;
import com.example.emsbackend.dto.events.entityDTO.EventEntityDTO;
import com.example.emsbackend.dto.events.entityDTO.ParameterEntityDTO;


import java.util.List;
import java.util.Map;

public interface ParameterService {

    void createParameter(ParameterEntityDTO parameterEntityDTO);


    List<String> getAllParameterIdentifiers();

    List<Long> getAllParameterIds();

    /**
     * Get the necessary information for dropdown menu for event parameter management
     * @return List of parameter information in the format [(id_1, identifier_code_1), (id_2, identifier_code_2), ...]
     */
    List<Map<String, String>> getDropdownInformation();


    List<ParameterEntityDTO> getAllParametersFiltered(ParameterEntitySearchCriteria searchCriteria);


    ParameterEntityDTO getParameterById(Long id);


    default void createParameters(List<ParameterEntityDTO> parameterEntityDTOList) {
        for (ParameterEntityDTO parameterEntityDTO: parameterEntityDTOList) {
            createParameter(parameterEntityDTO);
        }
    }

    List<EventEntityDTO> getEventEntityDTOListByParameterID(Long eventID);


    /**
     * 验证上报数据
     * @param
     * @param
     * @return
     */
    Boolean validateParameterValue(String inputValue, Long parameterID);

}

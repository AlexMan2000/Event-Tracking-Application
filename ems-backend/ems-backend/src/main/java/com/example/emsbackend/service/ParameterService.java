package com.example.emsbackend.service;

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

    List<ParameterEntityDTO> getParameterEntityDTOsForEventById(String eventID);

    /**
     * Parse a jsonEncodedString into a nested data structures
     * that stores the mapping from projectID to a list of pageID
     *
     * { "ProjId1": [pageId1, pageId2,...], "ProjId2": [pageId1, pageId3,...]}
     * @param jsonEncodedString
     * @return
     */
    Map<String, List<String>> parseParameterScope(String jsonEncodedString);


    /**
     * Parse a jsonEncodedString into a nested data structures
     * that stores the mapping from projectID to a list of pageID
     *
     * { "ProjId1": [pageId1, pageId2,...], "ProjId2": [pageId1, pageId3,...]}
     * @param jsonEncodedString
     * @return
     */
    Map<String, List<String>> parseParameterValue(String jsonEncodedString);

}

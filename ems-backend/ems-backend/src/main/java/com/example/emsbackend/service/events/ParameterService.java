package com.example.emsbackend.service.events;

import com.example.emsbackend.commons.status.Message;
import com.example.emsbackend.criteria_utils.searching.ParameterEntitySearchCriteria;
import com.example.emsbackend.dto.events.getDTO.GetIdentifiersDTO;
import com.example.emsbackend.dto.events.getDTO.ParameterEntityGetObjectDTO;
import com.example.emsbackend.dto.events.getDTO.ProjectEntityGetObjectDTO;
import com.example.emsbackend.dto.events.modifyDTO.ParameterEntityUpdateObjectDTO;


import java.util.List;

public interface ParameterService {

    List<ParameterEntityGetObjectDTO> getAllParameters();

    List<ParameterEntityGetObjectDTO> getAllParametersFiltered(ParameterEntitySearchCriteria searchCriteria);

    List<GetIdentifiersDTO> getAllMetaData();

    ParameterEntityGetObjectDTO getParameterById(Long parameterId);

    Message createParameter(ParameterEntityUpdateObjectDTO parameterEntityUpdateObjectDTO);

    Message updateParameter(ParameterEntityUpdateObjectDTO parameterEntityUpdateObjectDTO);

}

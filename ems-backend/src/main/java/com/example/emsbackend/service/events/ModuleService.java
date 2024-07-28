package com.example.emsbackend.service.events;

import com.example.emsbackend.commons.status.Message;
import com.example.emsbackend.criteria_utils.searching.ModuleEntitySearchCriteria;
import com.example.emsbackend.dto.events.getDTO.GetIdentifiersDTO;
import com.example.emsbackend.dto.events.getDTO.ModuleEntityGetObjectDTO;
import com.example.emsbackend.dto.events.modifyDTO.ModuleEntityUpdateObjectDTO;

import java.util.List;


public interface ModuleService {
    List<ModuleEntityGetObjectDTO> getAllModules();

    List<ModuleEntityGetObjectDTO> getAllModulesFiltered(ModuleEntitySearchCriteria searchCriteria);

    List<GetIdentifiersDTO> getAllMetaData();

    ModuleEntityGetObjectDTO getModuleById(Long moduleId);

    Message createModule(ModuleEntityUpdateObjectDTO projectEntityUpdateObjectDTO);

    Message updateModule(ModuleEntityUpdateObjectDTO projectEntityUpdateObjectDTO);


}

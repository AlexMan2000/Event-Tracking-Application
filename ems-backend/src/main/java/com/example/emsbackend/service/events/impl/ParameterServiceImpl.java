package com.example.emsbackend.service.events.impl;

import com.example.emsbackend.commons.enums.StatusCode;
import com.example.emsbackend.commons.status.Message;
import com.example.emsbackend.criteria_utils.searching.ParameterEntitySearchCriteria;
import com.example.emsbackend.criteria_utils.searching.impl.ParameterEntitySearchImpl;
import com.example.emsbackend.dto.events.getDTO.GetIdentifiersDTO;
import com.example.emsbackend.dto.events.getDTO.ParameterEntityGetObjectDTO;
import com.example.emsbackend.dto.events.modifyDTO.ParameterEntityUpdateObjectDTO;
import com.example.emsbackend.entity.events.entityEntity.ParameterEntity;
import com.example.emsbackend.repository.events.entityRepository.ParameterEntityRepository;
import com.example.emsbackend.service.events.ParameterService;
import com.example.emsbackend.service.utils.UtilityMethods;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParameterServiceImpl implements ParameterService {


    @Autowired
    private ParameterEntityRepository parameterEntityRepository;


    @Autowired
    private ParameterEntitySearchImpl parameterEntitySearch;

    @Autowired
    private UtilityMethods utilityMethods;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<ParameterEntityGetObjectDTO> getAllParameters() {
        return parameterEntityRepository.findAll().stream()
                .map(elem -> this.modelMapper.map(elem, ParameterEntityGetObjectDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ParameterEntityGetObjectDTO> getAllParametersFiltered(ParameterEntitySearchCriteria searchCriteria) {
        return parameterEntitySearch.getItemsFiltered(searchCriteria, ParameterEntity.class)
                .stream().map(elem -> this.modelMapper.map(elem, ParameterEntityGetObjectDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GetIdentifiersDTO> getAllMetaData() {
        return parameterEntityRepository.findAllMetaData().stream()
                .map(elem -> this.modelMapper.map(elem, GetIdentifiersDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ParameterEntityGetObjectDTO getParameterById(Long parameterId) {
        return parameterEntityRepository.findById(parameterId)
                .map(elem -> this.modelMapper.map(elem, ParameterEntityGetObjectDTO.class))
                .orElseThrow(() -> {throw new RuntimeException("Event with" + parameterId + "not found!");});
    }

    @Override
    public Message createParameter(ParameterEntityUpdateObjectDTO parameterEntityUpdateObjectDTO) {
        try {
            ParameterEntity parameterEntity = this.utilityMethods.recoverEntityFromUpdateDTO(
                    ParameterEntityUpdateObjectDTO.class
                    , ParameterEntity.class
                    , "event"
                    , parameterEntityUpdateObjectDTO
                    , 0
                    , List.of()
            );
            parameterEntityRepository.save(parameterEntity);
        }catch (Exception e) {
            e.printStackTrace();
            return new Message(StatusCode.CREATE_FAILURE, "Error Creating records");
        }
        return new Message(StatusCode.OK, "Successfully creating records");
    }

    @Override
    public Message updateParameter(ParameterEntityUpdateObjectDTO parameterEntityUpdateObjectDTO) {
        try {
            ParameterEntity parameterEntity = this.utilityMethods.recoverEntityFromUpdateDTO(
                    ParameterEntityUpdateObjectDTO.class
                    , ParameterEntity.class
                    , "parameter"
                    , parameterEntityUpdateObjectDTO
                    , 1
                    , List.of()
            );
            parameterEntityRepository.save(parameterEntity);
        }catch (Exception e) {
            e.printStackTrace();
            return new Message(StatusCode.CREATE_FAILURE, "Error inserting records");
        }
        return new Message(StatusCode.OK, "Successfully inserting records");
    }
}

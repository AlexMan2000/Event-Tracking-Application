package com.example.emsbackend.config.database.mysql;

import com.example.emsbackend.repository.events.entityRepository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class RepositoryMappingConfig {

    @Autowired
    private ModuleEntityRepository moduleEntityRepository;

    @Autowired
    private ParameterEntityRepository parameterEntityRepository;

    @Autowired
    private PageEntityRepository pageEntityRepository;

    @Autowired
    private ProjectEntityRepository projectEntityRepository;

    @Autowired
    private EventEntityRepository eventEntityRepository;

    @Bean(name = "repositoryMapping")
    public Map<String, JpaRepository<?, Long>> repositoryMap() {
        Map<String, JpaRepository<?, Long>> map = new HashMap<>();
        map.put("module", moduleEntityRepository);
        map.put("parameter", parameterEntityRepository);
        map.put("page", pageEntityRepository);
        map.put("project", projectEntityRepository);
        map.put("event", eventEntityRepository);
        return map;
    }
}


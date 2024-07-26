package com.example.emsbackend.criteria_utils.searching.impl;

import com.example.emsbackend.entity.events.entityEntity.ModuleEntity;
import com.example.emsbackend.entity.events.entityEntity.ProjectEntity;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


@Repository
public class ModuleEntitySearchImpl extends CriteriaFactoryDefaultImpl<ModuleEntity> {
    @Autowired
    public ModuleEntitySearchImpl(@Qualifier("eventEntityManagerFactory") EntityManager entityManager) {
        super(entityManager);
    }
}

package com.example.emsbackend.criteria_utils.searching.impl;

import com.example.emsbackend.entity.events.ParameterEntity;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


@Repository
public class ParameterEntitySearchImpl extends CriteriaFactoryDefaultImpl<ParameterEntity>{

    @Autowired
    public ParameterEntitySearchImpl(@Qualifier("eventEntityManagerFactory") EntityManager entityManager) {
        super(entityManager);
    }
}

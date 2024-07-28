package com.example.emsbackend.criteria_utils.searching.impl;

import com.example.emsbackend.entity.events.entityEntity.EventEntity;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


@Repository
public class EventEntitySearchImpl extends CriteriaFactoryDefaultImpl<EventEntity> {

    @Autowired
    public EventEntitySearchImpl(@Qualifier("eventEntityManagerFactory") EntityManager entityManager) {
        super(entityManager);
    }
}



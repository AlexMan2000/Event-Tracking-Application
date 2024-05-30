package com.example.emsbackend.repository.secondary;

import com.example.emsbackend.entity.secondary.EventPageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventPageMappingRepository extends JpaRepository<EventPageEntity, EventPageId> {
}

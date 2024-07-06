package com.example.emsbackend.repository.events;

import com.example.emsbackend.entity.events.EventEntity;
import com.example.emsbackend.entity.events.EventPageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventPageMappingRepository extends JpaRepository<EventPageEntity, EventPageId> {


    @Query(value = "SELECT event_id FROM event_page where page_id = ?1", nativeQuery = true)
    List<EventEntity> getEventsForPageByPageId(String pageId);

}

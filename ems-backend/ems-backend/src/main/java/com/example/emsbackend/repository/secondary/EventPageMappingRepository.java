package com.example.emsbackend.repository.secondary;

import com.example.emsbackend.entity.secondary.EventEntity;
import com.example.emsbackend.entity.secondary.EventPageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventPageMappingRepository extends JpaRepository<EventPageEntity, EventPageId> {


    @Query(value = "SELECT event_id FROM event_page where page_id = ?1", nativeQuery = true)
    List<EventEntity> getEventsForPageByPageId(String pageId);

}

package com.example.emsbackend.repository.events.mappingRepository;

import com.example.emsbackend.entity.events.entityEntity.EventEntity;
import com.example.emsbackend.entity.events.mappingEntity.EventPageEntity;
import com.example.emsbackend.repository.events.compositeId.EventPageId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventPageMappingRepository extends JpaRepository<EventPageEntity, EventPageId> {


    @Query(value = "SELECT event_id FROM event_page where page_id = ?1", nativeQuery = true)
    List<EventEntity> getEventsForPageByPageId(Long pageId);

}

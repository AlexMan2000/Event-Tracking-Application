package com.example.emsbackend.repository.events.mappingRepository;

import com.example.emsbackend.entity.events.entityEntity.EventEntity;
import com.example.emsbackend.entity.events.entityEntity.PageEntity;
import com.example.emsbackend.entity.events.mappingEntity.PageEventEntity;
import com.example.emsbackend.repository.events.compositeId.PageEventId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageEventMappingRepository extends JpaRepository<PageEventEntity, PageEventId> {


    @Query(value = "SELECT event_id FROM page_event where page_id = ?1", nativeQuery = true)
    List<EventEntity> getEventsForPageByPageId(Long pageId);

    @Query(value = "SELECT page_id FROM page_event where event_id = ?1", nativeQuery = true)
    List<PageEntity> getPagesForPageByEventId(Long eventId);

}

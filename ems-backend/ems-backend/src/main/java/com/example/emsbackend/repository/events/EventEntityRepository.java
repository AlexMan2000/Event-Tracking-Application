package com.example.emsbackend.repository.events;

import com.example.emsbackend.entity.events.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EventEntityRepository extends JpaRepository<EventEntity, String> {

        @Query(value = "SELECT * from event", nativeQuery = true)
        List<EventEntity> findAllEvents();

        EventEntity findEventEntityByIdentifierCode(String id);

        EventEntity findEventEntityById(String id);

        @Query(value = "update event where event.identifier_code = ?1", nativeQuery = true)
        void updateEventEntityByIdentifierCode(String id);

        void deleteEventEntitiesByIdentifierCode(String uid);
}

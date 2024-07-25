package com.example.emsbackend.repository.events.entityRepository;

import com.example.emsbackend.entity.events.entityEntity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EventEntityRepository extends JpaRepository<EventEntity, Long> {

        @Query(value = "SELECT * from event", nativeQuery = true)
        List<EventEntity> findAllEvents();


        @Query(value = "update event where event.id = ?1", nativeQuery = true)
        void updateEventEntityById(Long id);

        void deleteEventEntitiesByIdentifierCode(String uid);
}

package com.example.emsbackend.repository.secondary;

import com.example.emsbackend.entity.secondary.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EventEntityRepository extends JpaRepository<EventEntity, String> {

        EventEntity findEventEntityByIdentifierCode(String id);

        void deleteEventEntitiesByIdentifierCode(String uid);
}

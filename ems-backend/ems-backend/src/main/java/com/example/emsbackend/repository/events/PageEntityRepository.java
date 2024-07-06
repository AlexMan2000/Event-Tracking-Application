package com.example.emsbackend.repository.events;

import com.example.emsbackend.entity.events.PageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PageEntityRepository extends JpaRepository<PageEntity, String> {


    PageEntity findPageEntitiesByIdentifierCode(String id);
}

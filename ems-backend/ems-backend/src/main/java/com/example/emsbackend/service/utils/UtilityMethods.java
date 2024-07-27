package com.example.emsbackend.service.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;


@Service
public class UtilityMethods {

    @Autowired
    private ModelMapper modelMapper;

    private final Map<String, JpaRepository<?, Long>> repositoryMap;

    public UtilityMethods(@Qualifier("repositoryMapping") Map<String, JpaRepository<?, Long>> repositoryMap) {
        this.repositoryMap = repositoryMap;
    }


    private JpaRepository<?, Long> getRepositoryForRelation(String relationField) {
        JpaRepository<?, Long> repository = repositoryMap.get(relationField);
        if (repository == null) {
            throw new RuntimeException("No repository found for relation field: " + relationField);
        }
        return repository;
    }


    public <Entity, UpdateDTO> Entity recoverEntityFromUpdateDTO(
            Class<UpdateDTO> updateObjectClass
            , Class<Entity> targetEntityClass
            , String sourceRelation
            , Object updateObject
            , Integer mode
            , List<String> foreignRelationFields) {
        Entity newEntityObj = this.modelMapper.map(updateObject, targetEntityClass);
        Date nowTimeStamp = new Date();
        try {
            if (mode == 0) {
                // Creation
                Method createTimeSetter = targetEntityClass.getDeclaredMethod("setGmtCreate", Date.class);
                Method modifyTimeSetter = targetEntityClass.getDeclaredMethod("setGmtModify", Date.class);
                createTimeSetter.invoke(newEntityObj, nowTimeStamp);
                modifyTimeSetter.invoke(newEntityObj, nowTimeStamp);
            } else {
                // Update
                Method getIdMethod = updateObjectClass.getDeclaredMethod("getId");
                Long entityId = (Long) getIdMethod.invoke(updateObject);

                JpaRepository<?, Long> currEntityRepository = getRepositoryForRelation(sourceRelation);

                Optional<?> currentEntity = currEntityRepository.findById(entityId);

                if (currentEntity.isEmpty()) {
                    throw new RuntimeException("Entity not found with id: " + entityId);
                }
                Object currEntityObj = currentEntity.get();

                Method getGmtCreate = targetEntityClass.getDeclaredMethod("getGmtCreate");
                Method setGmtCreate = targetEntityClass.getDeclaredMethod("setGmtCreate", Date.class);
                Method setGmtModify = targetEntityClass.getDeclaredMethod("setGmtModify", Date.class);
                setGmtCreate.invoke(newEntityObj, (Date) getGmtCreate.invoke(currEntityObj));
                setGmtModify.invoke(newEntityObj, nowTimeStamp);



                Method setEntityOnlineTime = targetEntityClass.getDeclaredMethod("set"+capitalize(sourceRelation)+"OnlineTime", Date.class);
                Method getEntityOnlineTime = targetEntityClass.getDeclaredMethod("get"+capitalize(sourceRelation)+"OnlineTime");
                Method setEntityOfflineTime = targetEntityClass.getDeclaredMethod("set"+capitalize(sourceRelation)+"OfflineTime", Date.class);
                Method getEntityOfflineTime = targetEntityClass.getDeclaredMethod("get"+capitalize(sourceRelation)+"OfflineTime");

                setEntityOnlineTime.invoke(newEntityObj, (Date) getEntityOnlineTime.invoke(currEntityObj));
                setEntityOfflineTime.invoke(newEntityObj, (Date) getEntityOfflineTime.invoke(currEntityObj) );


                Method getEntityStatus = targetEntityClass.getDeclaredMethod("get"+capitalize(sourceRelation)+"Status");
                String currEntityStatus = (String) getEntityStatus.invoke(currEntityObj);
                String newEntityStatus = (String) getEntityStatus.invoke(newEntityObj);
                if (!currEntityStatus.equals("已上线") && newEntityStatus.equals("已上线")) {
                    setEntityOnlineTime.invoke(newEntityObj, nowTimeStamp);
                }
                if (currEntityStatus.equals("已上线") && !newEntityStatus.equals("已上线")) {
                    setEntityOfflineTime.invoke(newEntityObj, nowTimeStamp);
                }

            }

            // Handle foreign relations
            for (String relationField : foreignRelationFields) {
                Method getRelationIdsMethod = updateObjectClass.getDeclaredMethod("get" + capitalize(relationField) + "Ids");
                Set<Long> relationIds = (Set<Long>) getRelationIdsMethod.invoke(updateObject);

                Method setRelationMethod = targetEntityClass.getDeclaredMethod("set" + capitalize(relationField)+"s", Set.class);
                JpaRepository<?, Long> relationRepository = getRepositoryForRelation(relationField);
                Set<Object> relationEntities = new HashSet<>();

                for (Long relationId : relationIds) {
                    Optional<?> relationEntity = relationRepository.findById(relationId);
                    if (relationEntity.isEmpty()) {
                        throw new RuntimeException(relationField + " entity not found with id: " + relationId);
                    }
                    relationEntities.add(relationEntity.get());
                }

                setRelationMethod.invoke(newEntityObj, relationEntities);
            }

        } catch (NoSuchMethodException e) {
            System.out.println("No getStatus() method found for this object!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newEntityObj;
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}

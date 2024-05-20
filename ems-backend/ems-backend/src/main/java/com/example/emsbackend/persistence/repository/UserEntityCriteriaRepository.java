package com.example.emsbackend.persistence.repository;


import com.example.emsbackend.model.userSearch.UserEntityPage;
import com.example.emsbackend.model.userSearch.UserEntitySearchCriteria;
import com.example.emsbackend.persistence.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@Data
public class UserEntityCriteriaRepository {

    // Make sure entityManager is injected by Spring as a Bean
    @PersistenceContext
    private EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    @Autowired
    public UserEntityCriteriaRepository (EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
    }
    public List<UserEntity> findAllWithFilters(UserEntitySearchCriteria userEntitySearchCriteria) {
        /* --------------- Build the query ----------- */
        // The result of our query would be UserEntity type
        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> userEntityRoot = criteriaQuery.from(UserEntity.class);

        // Define our predicates
        Predicate predicate = getPredicate(userEntitySearchCriteria, userEntityRoot);
        // Apply all the predicates
        criteriaQuery.where(predicate);

        // Define the sort
        // Apply the sort


        /* -------------- Execute the query ------------ */
        TypedQuery<UserEntity> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }


    /**
     * Get all the EQUALS predicates from query
     * @param userEntitySearchCriteria
     * @param userEntityRoot
     * @return
     */
    private Predicate getPredicate(UserEntitySearchCriteria userEntitySearchCriteria, Root<UserEntity> userEntityRoot) {
        List<Predicate> predicates = new ArrayList<>();
        try {
            for (Method method : userEntitySearchCriteria.getClass().getDeclaredMethods()) {
                String methodName = method.getName();
                if (method.getName().startsWith("get")) {
                    String fieldName = methodName.substring(3);
                    fieldName = Character.toLowerCase(fieldName.charAt(0)) + fieldName.substring(1);
                    Object fieldValue = method.invoke(userEntitySearchCriteria);
                    if (Objects.nonNull(fieldValue)) {
                        predicates.add(
                                criteriaBuilder.equal(userEntityRoot.get(fieldName), fieldValue)
                        );
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        // criteriaBuilder only accepts Predicate[], so we need to transform List<Predicate> to Predicate[]
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

}

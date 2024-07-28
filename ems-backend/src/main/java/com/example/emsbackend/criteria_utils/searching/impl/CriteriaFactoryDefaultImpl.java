package com.example.emsbackend.criteria_utils.searching.impl;

import com.example.emsbackend.criteria_utils.searching.CriteriaFactory;
import com.example.emsbackend.criteria_utils.searching.SearchCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Data
public abstract class CriteriaFactoryDefaultImpl<T> implements CriteriaFactory<T> {


//    // Make sure entityManager is injected by Spring as a Bean
//    @PersistenceContext
    private EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

//    @Autowired
    public CriteriaFactoryDefaultImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
    }

    @Override
    public List<T> getItemsFiltered(SearchCriteria eventEntitySearchCriteria, Class<T> classObj) {

//        System.out.println(classObj);

        // 1. Create a query without filters
        CriteriaQuery<T> criteriaQuery = this.criteriaBuilder.createQuery(classObj);

        // 2. Get the database root object, used as the root filter
        Root<T> eventEntityRoot = criteriaQuery.from(classObj);

        // 3. Get the predicate
        Predicate predicate = getPredicate(eventEntitySearchCriteria, eventEntityRoot);

        // 4. Apply the predicate
        CriteriaQuery<T> filteredQuery = criteriaQuery.where(predicate);


        // 5. Build and execute the query
        TypedQuery<T> query = this.entityManager.createQuery(filteredQuery);
        return query.getResultList();
    }


    @Override
    public Predicate getPredicate(SearchCriteria searchCriteria, Root<T> queryRoot) {
        List<Predicate> predicates = new ArrayList<>();

        try {
            for (Method method : searchCriteria.getClass().getDeclaredMethods()) {
                String methodName = method.getName();
                if (method.getName().startsWith("get")) {
                    String fieldName = methodName.substring(3);
                    fieldName = Character.toLowerCase(fieldName.charAt(0)) + fieldName.substring(1);
                    Object fieldValue = method.invoke(searchCriteria);
                      if (Objects.nonNull(fieldValue)) {
                        predicates.add(
                                criteriaBuilder.equal(queryRoot.get(fieldName), fieldValue)
                        );
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

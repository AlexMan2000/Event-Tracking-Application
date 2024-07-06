package com.example.emsbackend.criteria_utils.searching.impl;


import com.example.emsbackend.entity.users.UserEntity;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
//@Data
public class UserEntitySearchImpl extends CriteriaFactoryDefaultImpl<UserEntity> {


    // Make sure entityManager is injected by Spring as a Bean
//    @PersistenceContext(unitName = "primary")
//    private EntityManager entityManager;


    @Autowired
    public UserEntitySearchImpl(@Qualifier("userEntityManagerFactory") EntityManager entityManager) {
        super(entityManager);
    }

    // Make sure entityManager is injected by Spring as a Bean
//    @PersistenceContext
//    private EntityManager entityManager;
//    private final CriteriaBuilder criteriaBuilder;
//
//    @Autowired
//    public UserEntityCriteriaRepository (EntityManager entityManager) {
//        this.entityManager = entityManager;
//        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
//    }
//    public List<UserEntity> findAllWithFilters(UserEntitySearchCriteria userEntitySearchCriteria) {
//        /* --------------- Build the query ----------- */
//        // The result of our query would be UserEntity type
//        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
//        System.out.println(UserEntity.class);
//        Root<UserEntity> userEntityRoot = criteriaQuery.from(UserEntity.class);
//
//        // Define our predicates
//        Predicate predicate = getPredicate(userEntitySearchCriteria, userEntityRoot);
//        // Apply all the predicates
//        criteriaQuery.where(predicate);
//
//        // Define the sort
//        // Apply the sort
//
//
//        /* -------------- Execute the query ------------ */
//        TypedQuery<UserEntity> query = entityManager.createQuery(criteriaQuery);
//        return query.getResultList();
//    }
//
//
//    /**
//     * Get all the EQUALS predicates from query
//     * @param userEntitySearchCriteria The search rules, {searchKey1: searchValue1, ...}. This is specified by the users' filter
//     * @param userEntityRoot The records in the database.
//     * @return
//     */
//    private Predicate getPredicate(UserEntitySearchCriteria userEntitySearchCriteria, Root<UserEntity> userEntityRoot) {
//        List<Predicate> predicates = new ArrayList<>();
//        try {
//            for (Method method : userEntitySearchCriteria.getClass().getDeclaredMethods()) {
//                String methodName = method.getName();
//                if (method.getName().startsWith("get")) {
//                    String fieldName = methodName.substring(3);
//                    fieldName = Character.toLowerCase(fieldName.charAt(0)) + fieldName.substring(1);
//                    Object fieldValue = method.invoke(userEntitySearchCriteria);
//                    if (Objects.nonNull(fieldValue)) {
//                        predicates.add(
//                                criteriaBuilder.equal(userEntityRoot.get(fieldName), fieldValue)
//                        );
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        // criteriaBuilder only accepts Predicate[], so we need to transform List<Predicate> to Predicate[]
//        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//    }

}

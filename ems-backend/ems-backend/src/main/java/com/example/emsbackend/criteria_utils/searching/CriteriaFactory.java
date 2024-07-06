package com.example.emsbackend.criteria_utils.searching;

import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;


public interface CriteriaFactory<T> {

    Predicate getPredicate(SearchCriteria searchCriteria, Root<T> queryRoot);

    List<T> getItemsFiltered(SearchCriteria searchCriteria, Class<T> classObj);
}

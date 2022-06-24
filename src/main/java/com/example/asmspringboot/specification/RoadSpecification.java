package com.example.asmspringboot.specification;

import com.example.asmspringboot.entity.Road;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDate;

public class RoadSpecification implements Specification<Road> {
    private SearchCriteria criteria;

    public RoadSpecification(SearchCriteria searchCriteria) {
        this.criteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Road> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (criteria.getOperation().equalsIgnoreCase(">=")) {
            if (root.get(criteria.getKey()).getJavaType() == LocalDate.class) {
                return criteriaBuilder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()), (LocalDate) criteria.getValue());
            }else {
                return criteriaBuilder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString());
            }
        } else if (criteria.getOperation().equalsIgnoreCase("<=")) {
            if (root.get(criteria.getKey()).getJavaType() == LocalDate.class) {
                return criteriaBuilder.lessThanOrEqualTo(
                        root.get(criteria.getKey()), (LocalDate) criteria.getValue());
            }else {
                return criteriaBuilder.lessThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString());
            }
        } else if (criteria.getOperation().equalsIgnoreCase("=")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return criteriaBuilder.like(
                        root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }


}

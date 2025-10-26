package com.example.zev.repository;

import com.example.zev.dto.request.FilterRequest;
import com.example.zev.dto.request.SearchRequest;
import com.example.zev.entity.BaseEntity;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class GenericSpecification <T extends BaseEntity> implements Specification<T> {

    private final SearchRequest request;


    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (request.getFilters() != null) {
            for (FilterRequest filter : request.getFilters()) {
                String field = filter.getField();
                String value = filter.getValue();
                String operator = filter.getOperator();

                if (!StringUtils.hasText(field) || !StringUtils.hasText(operator)) continue;

                Path<?> path = root.get(field);
                switch (operator.toLowerCase()) {
                    case "eq" -> predicates.add(cb.equal(path, value));
                    case "neq" -> predicates.add(cb.notEqual(path, value));
                    case "like" -> predicates.add(cb.like(cb.lower(path.as(String.class)), "%" + value.toLowerCase() + "%"));
                    case "gt" -> predicates.add(cb.greaterThan(path.as(String.class), value));
                    case "lt" -> predicates.add(cb.lessThan(path.as(String.class), value));
                    case "ge" -> predicates.add(cb.greaterThanOrEqualTo(path.as(String.class), value));
                    case "le" -> predicates.add(cb.lessThanOrEqualTo(path.as(String.class), value));
                    case "in" -> {
                        CriteriaBuilder.In<Object> inClause = cb.in(path);
                        for (String v : value.split(",")) inClause.value(v.trim());
                        predicates.add(inClause);
                    }
                }
            }
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}

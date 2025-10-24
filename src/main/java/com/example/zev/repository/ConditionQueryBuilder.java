package com.example.zev.repository;

import com.example.zev.constants.ErrorCode;
import com.example.zev.constants.FilterOperator;
import com.example.zev.constants.SortValue;
import com.example.zev.dto.request.FilterRequest;
import com.example.zev.dto.request.SearchRequest;
import com.example.zev.dto.request.SortRequest;
import com.example.zev.exception.BusinessException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.Metamodel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.*;

@Component
@RequiredArgsConstructor
public class ConditionQueryBuilder<T> {


    public List<Predicate> buildPredicates(List<FilterRequest> filters, Root<T> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        filters.forEach(filter -> predicates.add(this.getOperator(filter, cb, root)));
        return predicates;
    }

    public Predicate getOperator(FilterRequest filter, CriteriaBuilder cb, Root<T> root) {
        Path<String> path = getPath(root, filter.getField());
        String value = filter.getValue();
        Predicate predicate = cb.conjunction(); // doan nay co the return truc tiep trong switch
        switch (FilterOperator.valueOf(filter.getOperator())) {
            case FilterOperator.eq:
                predicate = cb.and(predicate, cb.equal(path, value));
            case FilterOperator.like:
                predicate = cb.and(predicate, cb.like(path, "%" + value + "%"));
            case FilterOperator.gt:
                predicate = cb.and(predicate, cb.greaterThan(path, value));
            case FilterOperator.lt:
                predicate = cb.and(predicate, cb.lessThan(path, value));
            default:
                // Bỏ qua nếu operator không hợp lệ
                break;
        }
        return predicate;
    }

    public void setLimit(TypedQuery<T> query, SearchRequest request) {
        query.setFirstResult(request.getPageNo() * request.getPageSize());
        query.setMaxResults(request.getPageSize());
    }

    public List<Order> buildOrders(List<SortRequest> sorts, CriteriaBuilder cb, Root<T> root) {
        List<Order> orders = new ArrayList<>();
        if (sorts == null) return orders;

        Set<String> validFields = getAllFields(root.getJavaType());
        for (SortRequest s : sorts) {
            if (!validFields.contains(s.getField())) continue;
            Path<?> path = root.get(s.getField());

            orders.add(
                    StringUtils.equals(SortValue.DESC.name(), s.getDirection())
                    ? cb.desc(path) : cb.asc(path)
            );
        }
        return orders;
    }

    private Set<String> getAllFields(Class<?> clazz) {
        Set<String> fields = new HashSet<>();
        while (clazz != null && !clazz.equals(Object.class)) {
            for (Field field : clazz.getDeclaredFields()) {
                fields.add(field.getName());
            }
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    private Path<String> getPath(Root<T> root, String fieldName) {
        try {
            return root.get(fieldName);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public Long count(CriteriaBuilder cb, List<Predicate> predicates, EntityManager em, Class<T> clazz) {
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<T> rootCount = countQuery.from(clazz);
        countQuery.select(cb.count(rootCount));
        countQuery.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(countQuery).getSingleResult();
    }

    public List<T> getItems(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, CriteriaQuery<T> cq,SearchRequest searchRequest, EntityManager entityManager) {
        cq.where(predicates.toArray(new Predicate[0]));

        if (!CollectionUtils.isEmpty(searchRequest.getSorts())) {
            List<Order> orders = this.buildOrders(searchRequest.getSorts(), cb, root);
            cq.orderBy(orders);
        }
        TypedQuery<T> query = entityManager.createQuery(cq);
        this.setLimit(query, searchRequest);
        return query.getResultList();
    }

}

package com.example.zev.repository;

import com.example.zev.constants.ErrorCode;
import com.example.zev.dto.request.FilterRequest;
import com.example.zev.dto.request.SearchRequest;
import com.example.zev.dto.response.ListResponse;
import com.example.zev.entity.BaseEntity;
import com.example.zev.exception.BusinessException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.Metamodel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Repository
@RequiredArgsConstructor
public class SearchRepositoryImpl<T extends BaseEntity> implements SearchRepository<T>{

    @PersistenceContext
    private final EntityManager entityManager;

    private final ConditionQueryBuilder<T> conditionQueryBuilder;


    @Override
    public ListResponse<T> search(SearchRequest searchRequest) throws ExecutionException, InterruptedException, BusinessException {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        Class<T> clazz = (Class<T>) this.getEntityClassByName(searchRequest.getEntity(), entityManager);
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> root = cq.from(clazz);
        List<Predicate> predicates = conditionQueryBuilder.buildPredicates(searchRequest.getFilters(), root, cb);
        CompletableFuture<List<T>> contentFuture = CompletableFuture.completedFuture(conditionQueryBuilder.getItems(predicates, root, cb, cq, searchRequest, entityManager));
        CompletableFuture<Long> totalFuture = CompletableFuture.completedFuture(conditionQueryBuilder.count(cb, predicates, entityManager, clazz));
        CompletableFuture.allOf(contentFuture, totalFuture).join();

        return ListResponse.<T>builder()
                .total(totalFuture.get())
                .items(contentFuture.get())
                .build();
    }


    public Class<?> getEntityClassByName(String entityName, EntityManager entityManager) throws BusinessException {
        Metamodel metamodel = entityManager.getMetamodel();
        for (EntityType<?> entityType : metamodel.getEntities()) {
            if (entityType.getName().equalsIgnoreCase(entityName)) {
                return entityType.getJavaType();
            }
        }
        throw new BusinessException(ErrorCode.ENTITY_NOT_FOUND);
    }
}

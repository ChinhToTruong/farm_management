package com.example.zev.service;

import com.example.zev.dto.request.SearchRequest;
import com.example.zev.entity.BaseEntity;
import com.example.zev.exception.BusinessException;
import org.springframework.data.domain.Page;

import java.util.List;

/*
* E: entity
* D: dto
* */
public interface CrudServiceV2<E extends BaseEntity, D> {

    D create(D t) throws BusinessException;

    D update(D t) throws BusinessException;

    D findById(Long id) throws BusinessException;

    List<D> findAll();

    void deleteById(Long id) throws BusinessException;

    void deleteList(List<Long> ids);

    Page<E> search(SearchRequest searchRequest) throws BusinessException;
}

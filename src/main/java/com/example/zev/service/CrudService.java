package com.example.zev.service;


import com.example.zev.dto.request.SearchRequest;
import com.example.zev.dto.response.ListResponse;
import com.example.zev.exception.BusinessException;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface CrudService<T> {

    T create(T t);

    T update(T t);

    T findById(Long id) throws BusinessException;

    List<T> findAll();

    void deleteById(Long id) throws BusinessException;

    void deleteList(List<Long> ids);

    ListResponse<T> search(SearchRequest searchRequest) throws ExecutionException, InterruptedException, BusinessException;
}

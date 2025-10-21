package com.example.zev.repository;


import com.example.zev.dto.request.SearchRequest;
import com.example.zev.dto.response.ListResponse;
import com.example.zev.entity.BaseEntity;
import com.example.zev.exception.BusinessException;

import java.util.concurrent.ExecutionException;

public interface SearchRepository<T extends BaseEntity> {
    ListResponse<T> search(SearchRequest searchRequest) throws ExecutionException, InterruptedException, BusinessException;
}

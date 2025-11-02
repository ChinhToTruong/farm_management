package com.example.zev.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

import java.util.List;


public interface BaseMapper<E, D> {

    D toDto(E entity);

    E create(D dto);
    void update(D dto, @MappingTarget E entity);

    List<D> toDtoList(List<E> entityList);

    List<E> toEntityList(List<D> dtoList);

}

package com.example.zev.service;

import com.example.zev.constants.ErrorCode;
import com.example.zev.constants.SortValue;
import com.example.zev.dto.request.SearchRequest;
import com.example.zev.dto.request.SortRequest;
import com.example.zev.entity.BaseEntity;
import com.example.zev.exception.BusinessException;
import com.example.zev.mapper.BaseMapper;
import com.example.zev.repository.CrudRepository;
import com.example.zev.repository.GenericSpecification;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public abstract class CrudServiceImplV2<E extends BaseEntity, D extends BaseEntity> implements CrudServiceV2<E, D>{
    @Setter(onMethod = @__({@Autowired}))
    private CrudRepository<E> repository;

    @Setter(onMethod_ = @__({@Autowired}))
    private ModelMapper modelMapper;

    @Setter(onMethod_ = @__({@Autowired}))
    private BaseMapper<E, D> baseMapper;


    @Override
    public D create(D t) throws BusinessException {
        E entity = baseMapper.create(t);
        entity = repository.save(entity);
        return baseMapper.toDto(entity);
    }

    @Override
    public D update(D t) throws BusinessException {
        E entity = this.findEntity(t.getId());
        baseMapper.update(t, entity);
        entity = repository.save(entity);
        return baseMapper.toDto(entity);
    }

    @Override
    public D findById(Long id) throws BusinessException {
        E entity = this.findEntity(id);
        return baseMapper.toDto(entity);
    }

    @Override
    public List<D> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) throws BusinessException {
        this.findById(id);
        repository.deleteById(id);
    }

    @Override
    public void deleteList(List<Long> ids) {
        repository.deleteAllById(ids);
    }

    @Override
    public Page<E> search(SearchRequest request) throws BusinessException {
        Specification<E> specification = new GenericSpecification<>(request);
        // build sort
        List<Sort.Order> orders = new ArrayList<>();
        if (request.getSorts() != null) {
            for (SortRequest s : request.getSorts()) {
                Sort.Direction dir = SortValue.DESC.name().equalsIgnoreCase(s.getDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;
                orders.add(new Sort.Order(dir, s.getField()));
            }
        }

        Pageable pageable = PageRequest.of(request.getPageNo(), request.getPageSize(), Sort.by(orders));
        return repository.findAll(specification, pageable);
    }

    public E findEntity(Long id) throws BusinessException {
        return this.repository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));
    }
}

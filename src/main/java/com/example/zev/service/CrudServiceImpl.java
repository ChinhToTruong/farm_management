package com.example.zev.service;

import com.example.zev.constants.ErrorCode;
import com.example.zev.constants.SortValue;
import com.example.zev.dto.request.SearchRequest;
import com.example.zev.dto.request.SortRequest;
import com.example.zev.entity.BaseEntity;
import com.example.zev.exception.BusinessException;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public abstract class CrudServiceImpl<T extends BaseEntity> implements CrudService<T> {

    @Setter(onMethod = @__({@Autowired}))
    private CrudRepository<T> repository;

    @Setter(onMethod_ = @__({@Autowired}))
    private ModelMapper modelMapper;


    @Override
    @Transactional
    public T create(T t) throws BusinessException {
        return repository.save(t);
    }

    @Override
    @Transactional
    public T update(T t) throws BusinessException {
        T old = this.findById(t.getId());
        modelMapper.map(t, old);
        return repository.save(old);
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(Long id) throws BusinessException {
        return repository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws BusinessException {
        this.findById(id);
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteList(List<Long> ids) {
        repository.deleteAllById(ids);
    }

    @Override
    public Page<T> search(SearchRequest request) throws BusinessException {
        Specification<T> specification = new GenericSpecification<>(request);
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


    protected <D extends BaseEntity> List<D> mappingChildren(
            Long parentId,
            CrudRepository<D> repo,
            String parentNameField
    ){
        Specification<D> spec = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(parentNameField), parentId);

        return repo.findAll(spec);
    }


    protected <D extends BaseEntity> List<D> saveChildren(
            Long parentId,
            CrudRepository<D> repo,
            String parentNameField,
            List<D> children
    ){
        List<Long> oldChild = this.mappingChildren(parentId, repo, parentNameField)
                .stream().map(D::getId).toList();
        repo.deleteAllById(oldChild);

        return repo.saveAll(children);
    }
}

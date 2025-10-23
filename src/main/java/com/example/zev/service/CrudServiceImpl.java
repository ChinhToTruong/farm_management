package com.example.zev.service;

import com.example.zev.constants.ErrorCode;
import com.example.zev.dto.request.SearchRequest;
import com.example.zev.dto.response.ListResponse;
import com.example.zev.entity.BaseEntity;
import com.example.zev.exception.BusinessException;
import com.example.zev.repository.CrudRepository;
import com.example.zev.repository.SearchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public abstract class CrudServiceImpl<T extends BaseEntity> implements CrudService<T> {

    @Setter(onMethod = @__({@Autowired}))
    private JpaRepository<T, Long> repository;


    @Override
    @Transactional
    public T create(T t) {
        return repository.save(t);
    }

    @Override
    @Transactional
    public T update(T t) {
        return null;
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

//    @Override
//    @Transactional(readOnly = true)
//    public ListResponse<T> search(SearchRequest searchRequest) throws ExecutionException, InterruptedException, BusinessException {
//        return searchRepository.search(searchRequest);
//    }

    protected <D extends BaseEntity> List<D> mappingChildren(
            Long parentId,
            CrudRepository<D> repo,
            String parentNameField
    ){
        Specification<D> spec = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(parentNameField), parentId);

        return repo.findAll(spec);
    }

    @Override
    public ListResponse<T> search(SearchRequest searchRequest) {
        return null;
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

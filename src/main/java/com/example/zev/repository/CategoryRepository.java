package com.example.zev.repository;

import com.example.zev.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category> {
}

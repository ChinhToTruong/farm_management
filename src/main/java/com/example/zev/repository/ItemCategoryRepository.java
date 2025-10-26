package com.example.zev.repository;

import com.example.zev.entity.Item;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoryRepository extends CrudRepository<Item>{
}

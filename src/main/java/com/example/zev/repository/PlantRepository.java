package com.example.zev.repository;

import com.example.zev.entity.Plant;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends CrudRepository<Plant> {
}

package com.example.zev.repository;

import com.example.zev.entity.Vaccination;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationRepository extends CrudRepository<Vaccination> {
}

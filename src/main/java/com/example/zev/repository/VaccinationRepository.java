package com.example.zev.repository;

import com.example.zev.entity.Vaccination;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationRepository extends CrudRepository<Vaccination> {

  @Query(value = """
      select v.vaccination_name,
               TO_CHAR(v.start_date, 'DD-MM-YYYY') as start_date,
               TO_CHAR(v.next_date, 'DD-MM-YYYY') as next_date,
               v.note,
               u.name as user_name,
               a.batch_name,
               'Chưa tiêm' as status
        from vaccinations v
                 left join users u
                           on v.user_id = u.id
                 left join animal_batch a
                           on v.animal_batch_id = a.id
    where v.user_id = :userId and v.start_date > NOW()
    """, nativeQuery = true)
  List<Object[]> findByUserId(@Param("userId") Long userId);
}

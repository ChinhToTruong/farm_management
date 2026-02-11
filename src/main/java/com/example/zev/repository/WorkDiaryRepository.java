package com.example.zev.repository;

import com.example.zev.dto.response.summary.WorkDiaryDailyStatusDTO;
import com.example.zev.entity.WorkDiary;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkDiaryRepository extends CrudRepository<WorkDiary> {

  @Query("""
    SELECT new com.example.zev.dto.response.summary.WorkDiaryDailyStatusDTO(
        w.workDate,
        w.status,
        COUNT(w.id)
    )
    FROM WorkDiary w
     WHERE (:userId IS NULL OR w.userId = :userId)
    GROUP BY w.workDate, w.status
    ORDER BY w.workDate, w.status
  """)
  List<WorkDiaryDailyStatusDTO> summarizeByDateAndStatus(
      @Param("userId") Long userId
  );

  int countWorkDiaryByUserIdAndStatus(Long userId, String status);

  @Query(value = """
      select w.task_name,
               w.description,
               TO_CHAR(w.work_date, 'DD-MM-YYYY'),
               s.season_name,
               u.name,
               p.plant_name,
               a.batch_name,
               'Chưa hoàn thành' as status
        from work_diaries w
                 left join users u
                           on w.user_id = u.id
                 left join plants p
                           on w.plant_id = p.id
                 left join animal_batch a
                           on w.batch_id = a.id
                 left join crop_seasons s
                           on w.crop_season_id = s.id
        where w.status = 'PENDING' and u.id = :userId
  """, nativeQuery = true)
  List<Object[]> findByUserId(@Param("userId") Long userId);
}

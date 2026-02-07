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
}

package com.example.zev.repository;

import com.example.zev.entity.InventoryTransaction;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryTransactionRepository extends CrudRepository<InventoryTransaction> {
  @Query(value = """
      select i.name,
                 case t.transaction_type
                     when 'IMPORT' then 'Nhập kho'
                     when 'CONSUME' then 'Xuất dùng'
                     when 'ADJUST' then 'Điều chỉnh'
                 end as transaction_type,
                 t.quantity,
                 t.unit_price,
                 t.total_amount,
                 TO_CHAR(t.transaction_date, 'DD-MM-YYYY') as transaction_date,
                 t.note,
                 ab.batch_name,
                 p.plant_name
          from inventory_transactions t
                   left join items i
                             on t.item_id = i.id
                   left join animal_batch ab
                             on t.related_animal_batch_id = ab.id
                   left join plants p
                             on t.related_plant_id = p.id
          order by t.transaction_date desc
    """, nativeQuery = true)
  List<Object[]> findExport();
}

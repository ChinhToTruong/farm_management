package com.example.zev.repository;

import com.example.zev.entity.InventoryTransaction;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryTransactionRepository extends CrudRepository<InventoryTransaction> {
}

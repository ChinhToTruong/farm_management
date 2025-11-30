package com.example.zev.service;

import com.example.zev.entity.AnimalBatch;
import com.example.zev.entity.InventoryTransaction;
import com.example.zev.entity.Item;
import com.example.zev.entity.Plant;
import com.example.zev.exception.BusinessException;
import com.example.zev.repository.InventoryTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InventoryTransactionService extends CrudServiceImpl<InventoryTransaction>{

//    private final InventoryTransactionRepository inventoryTransactionRepository;
//    private final AnimalBatchService animalBatchService;
//    private final PlantService plantService;
//    private final ItemService itemService;
}

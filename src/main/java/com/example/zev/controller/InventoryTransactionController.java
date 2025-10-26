package com.example.zev.controller;

import com.example.zev.entity.InventoryTransaction;
import com.example.zev.service.CategoryService;
import com.example.zev.service.InventoryTransactionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/inventory-transactions")
public class InventoryTransactionController extends BaseController<InventoryTransaction>{

    protected InventoryTransactionService service;

    public InventoryTransactionController(InventoryTransactionService service) {
        super(service);
        this.service = service;
    }
}

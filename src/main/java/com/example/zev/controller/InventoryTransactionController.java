//package com.example.zev.controller;
//
//import com.example.zev.dto.response.ResponseData;
//import com.example.zev.entity.InventoryTransaction;
//import com.example.zev.exception.BusinessException;
//import com.example.zev.service.InventoryTransactionExportService;
//import com.example.zev.service.InventoryTransactionService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//@RequestMapping("/inventory-transactions")
//public class InventoryTransactionController extends BaseController<InventoryTransaction>{
//    protected final InventoryTransactionService service;
//    private final InventoryTransactionExportService exportService;
//
//
//    public InventoryTransactionController(InventoryTransactionService service, InventoryTransactionExportService exportService) {
//        super(service);
//        this.service = service;
//        this.exportService = exportService;
//    }
//
//    @GetMapping("/export")
//    public ResponseData<?> exportExcel() throws BusinessException {
//        String reportName = "inventory-transactions";
//        return ResponseData.builder()
//                .data(exportService.exportExcel(reportName))
//                .build();
//    }
//}

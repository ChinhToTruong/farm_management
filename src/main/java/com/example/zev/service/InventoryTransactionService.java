package com.example.zev.service;

import com.example.zev.entity.AnimalBatch;
import com.example.zev.entity.InventoryTransaction;
import com.example.zev.entity.Item;
import com.example.zev.entity.Plant;
import com.example.zev.exception.BusinessException;
import com.example.zev.repository.InventoryTransactionRepository;
import com.example.zev.utils.AuthUtils;
import com.example.zev.utils.ExportExcelUtils;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InventoryTransactionService extends CrudServiceImpl<InventoryTransaction>{

    private final InventoryTransactionRepository inventoryTransactionRepository;
//    private final AnimalBatchService animalBatchService;
//    private final PlantService plantService;
//    private final ItemService itemService;

  public String exportExcel() throws IOException {
    Long userId = AuthUtils.getUserId();
    List<Object[]> rows = inventoryTransactionRepository.findExport();
    List<String> headers = List.of(
        "Tên vật tư",
        "Loại giao dịch",
        "Số lượng",
        "Đơn giá",
        "Thành tiền",
        "Ngày giao dịch",
        "Ghi chú",
        "Đàn vật nuôi",
        "Cây trồng"
    );

    return ExportExcelUtils.exportExcelBase64("Đợt tiêm phòng", headers, rows);
  }
}

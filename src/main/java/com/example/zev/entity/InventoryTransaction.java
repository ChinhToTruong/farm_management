package com.example.zev.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "InventoryTransaction")
@Table(name = "inventory_transactions")
@Data
@FieldNameConstants
public class InventoryTransaction extends BaseEntity implements ExportExcel{

    @ManyToOne
    private Item item; // Vật tư được nhập hoặc xuất

    @Pattern(regexp = "^IMPORT|CONSUME|ADJUST")
    private String transactionType; // Loại giao dịch: import / consume / adjust

    private Integer quantity; // Số lượng thay đổi

    private BigDecimal unitPrice; // Giá mỗi đơn vị (nếu có, dùng cho nhập)

    private BigDecimal totalAmount; // Thành tiền = quantity × unit_price

    private LocalDateTime transactionDate; // Ngày giao dịch

    private String note; // Ghi chú

    @ManyToOne
    private AnimalBatch relatedAnimalBatch; // Nếu liên quan đến đàn vật nuôi

    @ManyToOne
    private Plant relatedPlant; // Nếu liên quan đến cây trồng

    @Override
    public List<String> getExcelHeaders() {
        return List.of(
                "ID", "Vật tư", "Loại giao dịch", "Số lượng", "Đơn giá",
                "Thành tiền", "Ngày giao dịch", "Ghi chú",
                "Đàn vật nuôi", "Cây trồng", "Người tạo", "Ngày tạo", "Người sửa", "Ngày sửa"
        );
    }

    @Override
    public List<Object> getExcelData() {
        return List.of(
                getId(),
                item != null ? item.getName() : "",
                transactionType,
                quantity,
                unitPrice,
                totalAmount,
                transactionDate != null ? transactionDate.toString() : "",
                note,
                relatedAnimalBatch != null ? relatedAnimalBatch.getBatchName() : "",
                relatedPlant != null ? relatedPlant.getPlantName() : "",
                getCreatedBy(),
                getCreatedAt() != null ? getCreatedAt().toString() : "",
                getLastModifiedBy(),
                getLastModifiedAt() != null ? getLastModifiedAt().toString() : ""
        );
    }
}

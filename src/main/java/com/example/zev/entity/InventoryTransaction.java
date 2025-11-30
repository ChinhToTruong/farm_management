package com.example.zev.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;
import org.checkerframework.checker.units.qual.C;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "InventoryTransaction")
@Table(name = "inventory_transactions")
@Data
@FieldNameConstants
public class InventoryTransaction extends BaseEntity implements ExportExcel{

    @Transient
    private Item item; // Vật tư được nhập hoặc xuất

    @Column(name = "item_id")
    private Long itemId;

    @Pattern(regexp = "^IMPORT|CONSUME|ADJUST")
    private String transactionType; // Loại giao dịch: import / consume / adjust

    private Integer quantity; // Số lượng thay đổi

    private BigDecimal unitPrice; // Giá mỗi đơn vị (nếu có, dùng cho nhập)

    private BigDecimal totalAmount; // Thành tiền = quantity × unit_price

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime transactionDate; // Ngày giao dịch

    private String note; // Ghi chú

    @Transient
    private AnimalBatch relatedAnimalBatch; // Nếu liên quan đến đàn vật nuôi

    @Column(name = "related_animal_batch_id")
    private Long relatedAnimalBatchId;

    @Transient
    private Plant relatedPlant; // Nếu liên quan đến cây trồng

    @Column(name = "related_plant_id")
    private Long relatedPlantId;


    @Override
    public List<String> getExcelHeaders() {
        return List.of(
                "ID", "Vật tư", "Loại giao dịch", "Số lượng", "Đơn giá",
                "Thành tiền", "Ngày giao dịch", "Ghi chú",
                "Đàn vật nuôi", "Cây trồng", "Người tạo", "Ngày tạo", "Người sửa", "Ngày sửa"
        );
    }

    @JsonIgnore
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

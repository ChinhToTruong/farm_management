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

@EqualsAndHashCode(callSuper = true)
@Entity(name = "InventoryTransaction")
@Table(name = "inventory_transactions")
@Data
@FieldNameConstants
public class InventoryTransaction extends BaseEntity{

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

}

package com.zosh.zosh.pos.system.Payload.dto;

import com.zosh.zosh.pos.system.Model.Branch;
import com.zosh.zosh.pos.system.Model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@Builder
public class InventoryDto {

    private Long id ;

    private BranchDTO branch;

    private ProductDTO product;

    private long productId;
    private long branchId;

    private Integer quantity;

    private LocalTime lastUpdate;
}

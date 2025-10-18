package com.zosh.zosh.pos.system.mapper;

import com.zosh.zosh.pos.system.Model.Branch;
import com.zosh.zosh.pos.system.Model.Inventory;
import com.zosh.zosh.pos.system.Model.Product;
import com.zosh.zosh.pos.system.Payload.dto.InventoryDto;

public class InventoryMapper {

    public static InventoryDto toDTO(Inventory inventory){

        return InventoryDto.builder()
                .id(inventory.getId())
                .branchId(inventory.getBranch().getId())
                .quantity(inventory.getQuantity())
                .productId(inventory.getProduct().getId())
                .product(ProductMapper.toDto(inventory.getProduct()))

        .build();
    }

    public static Inventory toEntity(InventoryDto inventoryDto, Branch branch, Product product){

        return Inventory.builder()
                .id(inventoryDto.getId())
                .branch(branch)
                .quantity(inventoryDto.getQuantity())
                .product(product)

        .build();
    }
}

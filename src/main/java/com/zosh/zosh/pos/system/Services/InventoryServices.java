package com.zosh.zosh.pos.system.Services;

import com.zosh.zosh.pos.system.Payload.dto.InventoryDto;

import java.util.List;

public interface InventoryServices {
    InventoryDto createInventory(InventoryDto inventoryDto) throws Exception;
    InventoryDto updateInventory(Long id , InventoryDto inventoryDto) throws Exception;
    void deleteInventory(Long id) throws Exception;
    InventoryDto getInventoryById(Long id) throws Exception;
    InventoryDto getInventoryByProductIdAndBranchId(Long productId,Long branchId);
    List<InventoryDto> getInventoryByBranchId(Long productId);

}

package com.zosh.zosh.pos.system.controller;


import com.zosh.zosh.pos.system.Payload.Response.ApiResponse;
import com.zosh.zosh.pos.system.Payload.dto.InventoryDto;
import com.zosh.zosh.pos.system.Repository.InventoryRepository;
import com.zosh.zosh.pos.system.Services.InventoryServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventories")
public class InventoryController {

    public final InventoryServices inventoryServices;

    @PostMapping()
    public ResponseEntity<InventoryDto> create(@RequestBody InventoryDto inventoryDto) throws Exception {

        return ResponseEntity.ok(inventoryServices.createInventory(inventoryDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDto> update(@PathVariable Long id, @RequestBody InventoryDto inventoryDto) throws Exception {

        return ResponseEntity.ok(inventoryServices.updateInventory(id,inventoryDto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) throws Exception {
          inventoryServices.deleteInventory(id);
          ApiResponse apiResponse = new ApiResponse();
          apiResponse.setMessage("successfully deleted inventory");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/branch/{branchId}/product/{productId}")
    public ResponseEntity<InventoryDto> getInventoryByProductIdAndBranchId(Long branchId,Long productId) throws Exception {
        return ResponseEntity.ok(inventoryServices.getInventoryByProductIdAndBranchId(branchId,productId));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<InventoryDto>> getInventoryByBranch(@PathVariable Long branchId) throws Exception {
        return ResponseEntity.ok(inventoryServices.getInventoryByBranchId(branchId));
    }


}

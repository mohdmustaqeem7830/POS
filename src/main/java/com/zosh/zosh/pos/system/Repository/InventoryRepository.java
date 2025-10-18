package com.zosh.zosh.pos.system.Repository;

import com.zosh.zosh.pos.system.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
     Inventory findByProductIdAndBranchId(Long productId,Long branchId);
     List<Inventory> findByBranchId(Long branchId);

}

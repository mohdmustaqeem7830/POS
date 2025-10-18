package com.zosh.zosh.pos.system.Services.Impl;

import com.zosh.zosh.pos.system.Model.Branch;
import com.zosh.zosh.pos.system.Model.Inventory;
import com.zosh.zosh.pos.system.Model.Product;
import com.zosh.zosh.pos.system.Payload.dto.InventoryDto;
import com.zosh.zosh.pos.system.Repository.BranchRepository;
import com.zosh.zosh.pos.system.Repository.InventoryRepository;
import com.zosh.zosh.pos.system.Repository.ProductRepository;
import com.zosh.zosh.pos.system.Services.InventoryServices;
import com.zosh.zosh.pos.system.mapper.InventoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryServices {

    private final InventoryRepository inventoryRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;


    @Override
    public InventoryDto createInventory(InventoryDto inventoryDto) throws Exception {

        Branch branch = branchRepository.findById(inventoryDto.getBranchId()).orElseThrow(

                ()->new Exception("Branch not exist")

        );

        Product product = productRepository.findById(inventoryDto.getProductId()).orElseThrow(
                ()->new Exception("Product not exist")
        );


        Inventory inventory = InventoryMapper.toEntity(inventoryDto,branch,product);
       Inventory saveInventory =  inventoryRepository.save(inventory);



        return InventoryMapper.toDTO(saveInventory);
    }



    @Override
    public InventoryDto updateInventory(Long id ,InventoryDto inventoryDto) throws Exception {

        Inventory inventory = inventoryRepository.findById(id).orElseThrow(
                ()-> new Exception("Inventory Not Found")
        );

        inventory.setQuantity(inventoryDto.getQuantity());
        Inventory updatedInventory =  inventoryRepository.save(inventory);
        return InventoryMapper.toDTO(updatedInventory);
    }

    @Override
    public void deleteInventory(Long id) throws Exception {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(
                ()-> new Exception("Inventory Not Found")
        );

        inventoryRepository.delete(inventory);
    }

    @Override
    public InventoryDto getInventoryById(Long id) throws Exception {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(
                ()-> new Exception("Inventory Not Found")
        );

        return InventoryMapper.toDTO(inventory);
    }

    @Override
    public InventoryDto getInventoryByProductIdAndBranchId(Long productId, Long branchId) {
        Inventory inventory = inventoryRepository.findByProductIdAndBranchId(productId,branchId);


        return InventoryMapper.toDTO(inventory);
    }

    @Override
    public List<InventoryDto> getInventoryByBranchId(Long branchId) {
        List<Inventory> inventories = inventoryRepository.findByBranchId(branchId);

        return inventories.stream().map(InventoryMapper::toDTO).collect(Collectors.toList());
    }
}

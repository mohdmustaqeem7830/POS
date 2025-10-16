package com.zosh.zosh.pos.system.controller;


import com.zosh.zosh.pos.system.Exceptions.UserException;
import com.zosh.zosh.pos.system.Payload.Response.ApiResponse;
import com.zosh.zosh.pos.system.Payload.Response.AuthResponse;
import com.zosh.zosh.pos.system.Payload.dto.BranchDTO;
import com.zosh.zosh.pos.system.Services.BranchServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/branches")
public class BranchController {
    private final BranchServices branchServices;


    @PostMapping()
    public ResponseEntity<BranchDTO> createBranch(@RequestBody BranchDTO branchDTO) throws UserException {

        BranchDTO createdBranch = branchServices.createBranch(branchDTO);

        return ResponseEntity.ok(createdBranch);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable Long id) throws Exception {

        BranchDTO createdBranch = branchServices.getBranchById(id);

        return ResponseEntity.ok(createdBranch);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<BranchDTO> updateBranch(@PathVariable Long id, @RequestBody BranchDTO branchDTO) throws Exception {

        BranchDTO createdBranch = branchServices.updateBranch(id,branchDTO);


        return ResponseEntity.ok(createdBranch);


    }

    @GetMapping("/store/{id}")
    public ResponseEntity<List<BranchDTO>> getAllBranchByStoreId(@PathVariable Long storeId) throws Exception {

        List<BranchDTO> branches =  branchServices.getAllBranchesByStoreId(storeId);

        return ResponseEntity.ok(branches);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBranch(@PathVariable Long id) throws Exception {

      branchServices.deleteBranch(id);

      ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Successfully deleted branch");


        return ResponseEntity.ok(apiResponse);
    }



}

package com.zosh.zosh.pos.system.mapper;

import com.zosh.zosh.pos.system.Model.Branch;
import com.zosh.zosh.pos.system.Model.Store;
import com.zosh.zosh.pos.system.Payload.dto.BranchDTO;

public class BranchMapper {


    public static  BranchDTO toDto(Branch branch) {


        return  BranchDTO.builder()
                .id(branch.getId())
                .name(branch.getName())
                .address(branch.getAddress())
                .phone(branch.getPhone())
                .email(branch.getEmail())
                .openingTime(branch.getOpeningTime())
                .closingTime(branch.getClosingTime())
                .workingDays(branch.getWorkingDays())
                .storeId(branch.getStore()!=null?branch.getStore().getId():null)
                .createdAt(branch.getCreatedAt())
                .updatedAt(branch.getUpdatedAt())
                .build();

    }


    public static  Branch toEntity(BranchDTO branchDTO, Store store) {


        return  Branch.builder()
                .id(branchDTO.getId())
                .name(branchDTO.getName())
                .address(branchDTO.getAddress())
                .phone(branchDTO.getPhone())
                .email(branchDTO.getEmail())
                .openingTime(branchDTO.getOpeningTime())
                .closingTime(branchDTO.getClosingTime())
                .workingDays(branchDTO.getWorkingDays())
                .store(store)
                .createdAt(branchDTO.getCreatedAt())
                .updatedAt(branchDTO.getUpdatedAt())
                .build();

    }


}

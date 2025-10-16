package com.zosh.zosh.pos.system.Services;

import com.zosh.zosh.pos.system.Exceptions.UserException;
import com.zosh.zosh.pos.system.Model.User;
import com.zosh.zosh.pos.system.Payload.dto.BranchDTO;

import java.util.List;

public interface BranchServices {

    BranchDTO createBranch(BranchDTO branchDTO) throws UserException;
    BranchDTO updateBranch(Long id,BranchDTO branchDTO) throws Exception;

    void deleteBranch(Long id) throws Exception;
    List<BranchDTO> getAllBranchesByStoreId(Long storeId);
    BranchDTO getBranchById(Long id) throws Exception;


}

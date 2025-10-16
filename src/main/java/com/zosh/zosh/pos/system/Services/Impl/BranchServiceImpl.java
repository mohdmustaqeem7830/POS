package com.zosh.zosh.pos.system.Services.Impl;

import com.zosh.zosh.pos.system.Exceptions.UserException;
import com.zosh.zosh.pos.system.Model.Branch;
import com.zosh.zosh.pos.system.Model.Store;
import com.zosh.zosh.pos.system.Model.User;
import com.zosh.zosh.pos.system.Payload.dto.BranchDTO;
import com.zosh.zosh.pos.system.Repository.BranchRepository;
import com.zosh.zosh.pos.system.Repository.StoreRepository;
import com.zosh.zosh.pos.system.Repository.UserRepository;
import com.zosh.zosh.pos.system.Services.BranchServices;
import com.zosh.zosh.pos.system.Services.UserService;
import com.zosh.zosh.pos.system.mapper.BranchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchServices {
    private final BranchRepository branchRepository;
    private final UserService userService;
    private final StoreRepository storeRepository;


    @Override
    public BranchDTO createBranch(BranchDTO branchDTO) throws UserException {

        User currentUser = userService.getCurrentUser();
        Store store = storeRepository.findByStoreAdminId(currentUser.getId());

        Branch branch = BranchMapper.toEntity(branchDTO,store);
        Branch saveBranch = branchRepository.save(branch);

        return BranchMapper.toDto(saveBranch);
    }

    @Override
    public BranchDTO updateBranch(Long id, BranchDTO branchDTO) throws Exception {

        Branch existing = branchRepository.findById(id).orElseThrow(
                ()-> new Exception("Store Not Found")
        );

        existing.setName(branchDTO.getName());
        existing.setWorkingDays(branchDTO.getWorkingDays());
        existing.setEmail(branchDTO.getEmail());
        existing.setAddress(branchDTO.getAddress());
        existing.setPhone(branchDTO.getPhone());
        existing.setOpeningTime(branchDTO.getOpeningTime());
        existing.setClosingTime(branchDTO.getClosingTime());
        existing.setUpdatedAt(LocalDateTime.now());


        Branch updatedBranch = branchRepository.save(existing);

        return BranchMapper.toDto(updatedBranch);

    }

    @Override
    public void deleteBranch(Long id) throws Exception {

        Branch existing = branchRepository.findById(id).orElseThrow(
                ()-> new Exception("Store Not Found")
        );



        branchRepository.delete(existing);


    }



    @Override
    public List<BranchDTO> getAllBranchesByStoreId(Long storeId) {

        List<Branch> branches = branchRepository.findByStoreId(storeId);

        return branches.stream().map(BranchMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BranchDTO getBranchById(Long id) throws Exception {
        Branch existing = branchRepository.findById(id).orElseThrow(
                ()-> new Exception("Store Not Found")
        );

        return BranchMapper.toDto(existing);
    }
}

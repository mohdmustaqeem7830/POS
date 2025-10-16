package com.zosh.zosh.pos.system.Repository;

import com.zosh.zosh.pos.system.Model.Branch;
import com.zosh.zosh.pos.system.Payload.dto.BranchDTO;
import org.aspectj.apache.bcel.util.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch,Long> {

    List<Branch> findByStoreId(Long storeId);
}

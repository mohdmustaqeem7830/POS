package com.zosh.zosh.pos.system.Repository;

import com.zosh.zosh.pos.system.Domain.UserRole;
import com.zosh.zosh.pos.system.Model.Store;
import com.zosh.zosh.pos.system.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String username);
    List<User> findByStore(Store store);
    List<User> findByBranchId(Long branchId);
}

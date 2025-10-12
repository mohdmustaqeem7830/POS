package com.zosh.zosh.pos.system.Repository;

import com.zosh.zosh.pos.system.Model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,Long> {

    Store findByStoreAdminId(Long adminId);
}

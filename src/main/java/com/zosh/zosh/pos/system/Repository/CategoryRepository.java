package com.zosh.zosh.pos.system.Repository;

import com.zosh.zosh.pos.system.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findByStoreId(Long storeId);
}

package com.zosh.zosh.pos.system.Repository;

import com.zosh.zosh.pos.system.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}

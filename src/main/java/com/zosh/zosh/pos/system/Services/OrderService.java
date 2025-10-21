package com.zosh.zosh.pos.system.Services;

import com.zosh.zosh.pos.system.Domain.OrderStatus;
import com.zosh.zosh.pos.system.Domain.PaymentType;
import com.zosh.zosh.pos.system.Model.Order;
import com.zosh.zosh.pos.system.Payload.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO createOrder(OrderDTO orderDTO) throws Exception;
    OrderDTO getOrderById(Long orderId) throws Exception;
    List<OrderDTO>  getOrdersByBranchId(Long branchId,
                                        Long customerId,
                                        Long cashierId,
                                        PaymentType paymentType,
                                        OrderStatus status
                                        ) throws Exception;
    List<OrderDTO>  getOrderByCashier(Long cashierId);
    void deleteOrder(Long id) throws Exception;

    List<OrderDTO> getTodayOrdersByBranch(Long branchId) throws Exception;
    List<OrderDTO> getOrdersByCustomerId(Long customerId) throws Exception;
    List<OrderDTO> getTop5RecentOrdersByBranchId(Long branchId) throws Exception;

}

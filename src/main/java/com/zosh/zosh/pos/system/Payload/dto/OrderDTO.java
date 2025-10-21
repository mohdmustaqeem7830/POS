package com.zosh.zosh.pos.system.Payload.dto;

import com.zosh.zosh.pos.system.Domain.PaymentType;
import com.zosh.zosh.pos.system.Model.Branch;
import com.zosh.zosh.pos.system.Model.Customer;
import com.zosh.zosh.pos.system.Model.OrderItem;
import com.zosh.zosh.pos.system.Model.User;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderDTO {
    private Long id ;

    private Double totalAmount;

    private LocalDateTime createdAt;


    private Long branchId;
    private Long  customerId;

    private BranchDTO branch;

    private UserDto cashier;

    private PaymentType paymentType;
    private Customer customer;

    private List<OrderItemDTO> items;


}

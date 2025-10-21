package com.zosh.zosh.pos.system.mapper;

import com.zosh.zosh.pos.system.Model.Order;
import com.zosh.zosh.pos.system.Payload.dto.OrderDTO;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDTO toDto(Order order) {

        return OrderDTO.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .branchId(order.getBranch().getId())
                .cashier(UserMapper.toDto(order.getCashier()))
                .customer(order.getCustomer())
                .paymentType(order.getPaymentType())
                .createdAt(order.getCreatedAt())
                .items(order.getItems().stream().map(OrderItemMapper :: toDto).collect(Collectors.toList()))

                .build();
    }
}

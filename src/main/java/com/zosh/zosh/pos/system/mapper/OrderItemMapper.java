package com.zosh.zosh.pos.system.mapper;

import com.zosh.zosh.pos.system.Model.Order;
import com.zosh.zosh.pos.system.Model.OrderItem;
import com.zosh.zosh.pos.system.Payload.dto.OrderItemDTO;

public class OrderItemMapper {

    public static OrderItemDTO toDto(OrderItem item) {

        if(item == null) return null;
        return OrderItemDTO.builder()
                .id(item.getId())
                .productId(item.getProduct().getId())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .product(ProductMapper.toDto(item.getProduct()))
                .build();
    }
}

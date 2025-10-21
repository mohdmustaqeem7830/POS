package com.zosh.zosh.pos.system.Payload.dto;

import com.zosh.zosh.pos.system.Model.Order;
import com.zosh.zosh.pos.system.Model.Product;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemDTO {

    private Long id ;

    private Integer quantity;

    private Double price;

    private ProductDTO product;

    private Long productId;

  private Long orderId;

}

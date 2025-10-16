package com.zosh.zosh.pos.system.Payload.dto;

import com.zosh.zosh.pos.system.Model.Store;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class ProductDTO {
    private Long id ;

    private String name;

    private String sku;

    private String description;

    private Double mrp;


    private Double sellingPrice;

    private String brand;

    private String image;

       private CategoryDTO categoryDTO;

    private Long categoryId;

    private Long storeId ;

    @Column(columnDefinition = "datetime")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "datetime")
    private LocalDateTime updatedAt;

}

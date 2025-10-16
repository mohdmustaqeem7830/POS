package com.zosh.zosh.pos.system.mapper;

import com.zosh.zosh.pos.system.Model.Category;
import com.zosh.zosh.pos.system.Model.Product;
import com.zosh.zosh.pos.system.Model.Store;
import com.zosh.zosh.pos.system.Payload.dto.ProductDTO;
import com.zosh.zosh.pos.system.Services.ProductServices;

public class ProductMapper {

    public static ProductDTO toDto(Product product){
        return   ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .description(product.getDescription())
                .mrp(product.getMrp())
                .sellingPrice(product.getSellingPrice())
                .brand(product.getBrand())
                .categoryDTO( CategoryMapper.toDTO(product.getCategory()))
                .storeId(product.getStore()!=null ?product.getStore().getId():null)
                .image(product.getImage())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                //.categoryId(product.get)

                .build();
    }


    public static Product toEntity(ProductDTO productDTO, Store store, Category category){
        return   Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .store(store)
                .category(category)
                .sku(productDTO.getSku())
                .description(productDTO.getDescription())
                .mrp(productDTO.getMrp())
                .sellingPrice(productDTO.getSellingPrice())
                .brand(productDTO.getBrand())
                .build();
//                .storeId(productDTO.getStore()!=null ?productDTO.getStore().getId():null)
//                .image(productDTO.getImage())
//                .createdAt(productDTO.getCreatedAt())
//                .updatedAt(productDTO.getUpdatedAt())
//                 .categoryId(product.get)

    }
}

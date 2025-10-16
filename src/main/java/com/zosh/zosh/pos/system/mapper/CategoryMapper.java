package com.zosh.zosh.pos.system.mapper;

import com.zosh.zosh.pos.system.Model.Category;
import com.zosh.zosh.pos.system.Payload.dto.CategoryDTO;
import lombok.Builder;
import lombok.Data;

@Data

public class CategoryMapper {


    public static CategoryDTO toDTO(Category  category){


        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .storeId(category.getStore()!=null?category.getStore().getId():null)
                .build();

    }


    public static Category toEntity(CategoryDTO  categoryDTO){


        return Category.builder()
                .name(categoryDTO.getName())
                .build();

    }
}

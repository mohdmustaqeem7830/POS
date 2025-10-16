package com.zosh.zosh.pos.system.Payload.dto;

import com.zosh.zosh.pos.system.Model.Store;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDTO {

    private Long id ;

    private String name;

    private Long storeId;
}

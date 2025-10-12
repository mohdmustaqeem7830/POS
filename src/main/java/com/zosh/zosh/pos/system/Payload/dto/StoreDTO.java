package com.zosh.zosh.pos.system.Payload.dto;

import com.zosh.zosh.pos.system.Domain.StoreStatus;
import com.zosh.zosh.pos.system.Model.StoreContact;
import com.zosh.zosh.pos.system.Model.User;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class StoreDTO {

    private Long id;

    private String brand;

    private UserDto storeAdmin;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;

    private String storeType;

    private StoreStatus status;

    private StoreContact contact ;

}

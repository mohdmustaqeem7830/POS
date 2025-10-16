package com.zosh.zosh.pos.system.Payload.dto;

import com.zosh.zosh.pos.system.Model.Store;
import com.zosh.zosh.pos.system.Model.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
@Data
@Builder
public class BranchDTO {

    private Long id ;
    private String name;
    private String address;
    private String email;
    private String phone;
    private List<String> workingDays;

    private LocalTime openingTime;
    private LocalTime closingTime;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private StoreDTO store;
    private Long storeId;
    private UserDto manager;
}

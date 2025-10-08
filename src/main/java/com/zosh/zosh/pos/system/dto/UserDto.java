package com.zosh.zosh.pos.system.dto;

import com.zosh.zosh.pos.system.Domain.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class UserDto {

    private Long id ;
    private String fullName ;

    private String email;

    private UserRole role;

    private String phone ;

    private String password;

    @Column(columnDefinition = "datetime")
    private LocalDateTime createdAt;
    @Column(columnDefinition = "datetime")
    private LocalDateTime updatedAt;
    @Column(columnDefinition = "datetime")
    private LocalDateTime lastlogin;

}

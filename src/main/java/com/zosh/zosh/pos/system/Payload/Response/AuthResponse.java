package com.zosh.zosh.pos.system.Payload.Response;

import com.zosh.zosh.pos.system.Payload.dto.UserDto;
import lombok.Data;

@Data
public class AuthResponse {

    private String jwt ;
    private String message ;
     private UserDto user ;


}

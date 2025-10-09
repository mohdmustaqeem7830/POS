package com.zosh.zosh.pos.system.mapper;

import com.zosh.zosh.pos.system.Model.User;
import com.zosh.zosh.pos.system.Payload.dto.UserDto;

public class UserMapper {
    public static UserDto toDto(User saveUser) {

        UserDto userDto  = new UserDto();
        userDto.setId(saveUser.getId());
        userDto.setFullName(saveUser.getFullName());
        userDto.setEmail(saveUser.getEmail());
        userDto.setRole(saveUser.getRole());
        userDto.setCreatedAt(saveUser.getCreatedAt());
        userDto.setUpdatedAt(saveUser.getUpdatedAt());
        userDto.setLastlogin(saveUser.getLastlogin());
        userDto.setPhone(saveUser.getPhone());


        return  userDto;
    }
}

package com.zosh.zosh.pos.system.mapper;

import com.zosh.zosh.pos.system.Model.User;
import com.zosh.zosh.pos.system.Payload.dto.UserDto;

public class UserMapper {
    public static UserDto toDto(User saveUser) {

        UserDto userDto  = new UserDto();
        userDto.setId(saveUser.getId());
        userDto.setFullName(saveUser.getFullName());
        userDto.setEmail(saveUser.getEmail());
        userDto.setPassword(saveUser.getPassword());
        userDto.setRole(saveUser.getRole());
        userDto.setCreatedAt(saveUser.getCreatedAt());
        userDto.setUpdatedAt(saveUser.getUpdatedAt());
        userDto.setLastlogin(saveUser.getLastlogin());
        userDto.setPhone(saveUser.getPhone());
        userDto.setStoreId(saveUser.getStore()!=null? saveUser.getStore().getId():null);
        userDto.setBranchId(saveUser.getBranch()!=null? saveUser.getBranch().getId():null);


        return  userDto;
    }


    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setCreatedAt(userDto.getCreatedAt());
        user.setUpdatedAt(userDto.getUpdatedAt());
        user.setLastlogin(userDto.getLastlogin());
        user.setPhone(userDto.getPhone());
        return user;
    }
}

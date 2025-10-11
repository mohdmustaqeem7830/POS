package com.zosh.zosh.pos.system.controller;


import com.zosh.zosh.pos.system.Exceptions.UserException;
import com.zosh.zosh.pos.system.Model.User;
import com.zosh.zosh.pos.system.Payload.dto.UserDto;
import com.zosh.zosh.pos.system.Services.UserService;
import com.zosh.zosh.pos.system.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @GetMapping("/profile")
     public ResponseEntity<UserDto> getUserProfile(@RequestHeader("Authorization") String jwt) throws UserException {

         User user =  userService.getUserFromJwtToken(jwt);
         return ResponseEntity.ok(UserMapper.toDto(user));
     }

     @GetMapping("/{id}")
     public ResponseEntity<UserDto> getUserById(@RequestHeader("Authorization") String jwt ,@PathVariable() Long id) throws UserException {

         User user =  userService.getUserById(id);
         return ResponseEntity.ok(UserMapper.toDto(user));
     }
}

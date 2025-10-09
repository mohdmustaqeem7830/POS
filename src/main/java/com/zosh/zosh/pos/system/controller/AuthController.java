package com.zosh.zosh.pos.system.controller;

import com.zosh.zosh.pos.system.Exceptions.UserException;
import com.zosh.zosh.pos.system.Payload.Response.AuthResponse;
import com.zosh.zosh.pos.system.Services.AuthService;
import com.zosh.zosh.pos.system.Payload.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private  final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler(@RequestBody UserDto userDto) throws UserException{
        System.out.println("Hello world");
        AuthResponse authResponse = authService.signup(userDto);

        return   ResponseEntity.ok(authResponse);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody UserDto userDto) throws UserException{

        return   ResponseEntity.ok(authService.login(userDto));
    }

}

package com.zosh.zosh.pos.system.Services;

import com.zosh.zosh.pos.system.Exceptions.UserException;
import com.zosh.zosh.pos.system.Payload.Response.AuthResponse;
import com.zosh.zosh.pos.system.Payload.dto.UserDto;

public interface AuthService {

  AuthResponse signup(UserDto userDto) throws UserException;
  AuthResponse login(UserDto userDto) throws UserException;

}

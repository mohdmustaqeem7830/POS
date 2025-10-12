package com.zosh.zosh.pos.system.Services.Impl;

import com.zosh.zosh.pos.system.Exceptions.UserException;
import com.zosh.zosh.pos.system.Model.User;
import com.zosh.zosh.pos.system.Repository.UserRepository;
import com.zosh.zosh.pos.system.Services.UserService;
import com.zosh.zosh.pos.system.configuration.JWTProvider;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository ;
    private final JWTProvider jwtProvider;

    @Override
    public User getUserFromJwtToken(String token) throws UserException {

        String email = jwtProvider.getEmailFromToken(token);
        User user  = userRepository.findByEmail(email);

        if (user==null){
            throw new UserException("Invalid User");
        }
        return user;
    }

    @Override
    public User getCurrentUser() throws UserException {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if(user==null){
            throw  new UserException("User Not Found");
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws UserException {
        User user = userRepository.findByEmail(email);
        if(user==null){
            throw  new UserException("User Not Found");
        }
        return user;
    }

    @Override
    public User getUserById(Long id) throws Exception {
       User user = userRepository.findById(id).orElseThrow(
               ()->new Exception("user not found")
       );
       return  user ;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers;
    }
}

package com.zosh.zosh.pos.system.Services.Impl;

import com.zosh.zosh.pos.system.Domain.UserRole;
import com.zosh.zosh.pos.system.Exceptions.UserException;
import com.zosh.zosh.pos.system.Model.User;
import com.zosh.zosh.pos.system.Payload.Response.AuthResponse;
import com.zosh.zosh.pos.system.Repository.UserRepository;
import com.zosh.zosh.pos.system.Services.AuthService;
import com.zosh.zosh.pos.system.configuration.JWTProvider;
import com.zosh.zosh.pos.system.dto.UserDto;
import com.zosh.zosh.pos.system.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.jca.support.LocalConnectionFactoryBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl  implements AuthService {

    private final UserRepository userRepository ;
    private final PasswordEncoder passwordEncoder ;
    private final JWTProvider jwtProvider;
    private final CustomUserImplementation customUserImplementation;




    @Override
    public AuthResponse signup(UserDto userDto) throws UserException {

        User user  = userRepository.findByEmail(userDto.getEmail());
        if (user!=null){
            throw new UserException("User already exist !");
        }

        if (userDto.getRole().equals(UserRole.ROLE_ADMIN)){
            throw new UserException("Role admin is not allowed");
        }

        User newuser = new User();
        newuser.setEmail(userDto.getEmail());
        newuser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newuser.setRole(userDto.getRole());
        newuser.setFullName(userDto.getFullName());
        newuser.setPhone(userDto.getPhone());
        newuser.setLastlogin(LocalDateTime.now());
        newuser.setCreatedAt(LocalDateTime.now());
        newuser.setUpdatedAt(LocalDateTime.now());


        User saveUser =  userRepository.save(newuser);


        Authentication authentication  = new UsernamePasswordAuthenticationToken(userDto.getEmail(),userDto.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registered Successfully");
        authResponse.setUser(UserMapper.toDto(saveUser));

        return authResponse;
    }

    @Override
    public AuthResponse login(UserDto userDto) throws UserException {

        String email = userDto.getEmail();
        String password = userDto.getPassword();

        Authentication authentication = authenticate(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String role = authorities.iterator().next().getAuthority();

        String jwt = jwtProvider.generateToken(authentication);

        User user    = userRepository.findByEmail(email);
        user.setLastlogin(LocalDateTime.now());

        userRepository.save(user);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login Successfully");
        authResponse.setUser(UserMapper.toDto(user));


        return authResponse;
    }

    private Authentication authenticate(String email, String password) throws UserException {

        UserDetails userDetails = customUserImplementation.loadUserByUsername(email);


        if(userDetails==null){
            throw new UserException("Email id doesn't exist" + email);
        }

        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw  new UserException("Password doesn't match");

        }
     return  new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}

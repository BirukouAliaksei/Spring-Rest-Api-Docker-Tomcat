package com.myproject.rest;


import com.myproject.config.security.CustomUserDetails;
import com.myproject.config.security.JwtProvider;
import com.myproject.domain.entity.User;
import com.myproject.domain.enums.Role;
import com.myproject.dto.dto.AuthRequest;
import com.myproject.dto.dto.AuthResponse;
import com.myproject.dto.dto.UserDto;
import com.myproject.dto.mapper.UserMapper;
import com.myproject.serviceapi.UserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UserServiceApi userServiceApi;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/registration")
    public UserDto registerUser(@RequestBody UserDto userDto) {
//        User user = new User();
//        user.setPassword(userDto.getPassword());
//        user.setUsername(userDto.getUsername());
        return userServiceApi.save(userDto);
    }

    @PostMapping("/auth/login")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        User user = userServiceApi.findByLoginAndPassword(request.getUsername(), request.getPassword());
        String token = jwtProvider.generateToken(user.getUsername(), user.getRole().getAuthority());
        return new AuthResponse(token);
    }
}

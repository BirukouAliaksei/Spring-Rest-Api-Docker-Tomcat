package com.myproject.rest;


import com.myproject.config.security.JwtProvider;
import com.myproject.domain.entity.User;
import com.myproject.dto.dto.AuthRequest;
import com.myproject.dto.dto.AuthResponse;
import com.myproject.dto.dto.UserDto;
import com.myproject.dto.mapper.UserMapper;
import com.myproject.serviceapi.UserServiceApi;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j
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
        return userServiceApi.userRegistration(userDto);
    }

    @PostMapping("/auth/login")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        User user = userServiceApi.findByLoginAndPassword(request.getUsername(), request.getPassword());
        String token = jwtProvider.generateToken(user.getUsername(), user.getRole().getAuthority());
        return new AuthResponse(token);
    }

}

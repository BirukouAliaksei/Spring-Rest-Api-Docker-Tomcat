package com.myproject.dto.dto;

import lombok.Data;

@Data
public class UserDto {

    private int id;

    private String username;

    private String email;

    private String password;

    private String role;


    public UserDto() {
    }

//    public UserDto(String username, String email, String password, String role) {
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.role = role;
//    }
}

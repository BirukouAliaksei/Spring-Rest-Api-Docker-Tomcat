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

}

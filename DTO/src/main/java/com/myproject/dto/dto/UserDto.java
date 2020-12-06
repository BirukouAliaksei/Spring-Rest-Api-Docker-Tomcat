package com.myproject.dto.dto;

import com.myproject.domain.entity.History;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {

    private int id;

    private String username;

    private String email;

    private String password;

    private String role;

//    private Set<History> histories;

}

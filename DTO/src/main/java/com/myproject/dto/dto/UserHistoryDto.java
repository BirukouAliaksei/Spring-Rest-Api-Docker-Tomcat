package com.myproject.dto.dto;

import com.myproject.domain.entity.History;

import java.util.Set;

public class UserHistoryDto {

    private int id;

    private String username;

    private String email;

    private String password;

    private String role;

    private Set<History> histories;
}

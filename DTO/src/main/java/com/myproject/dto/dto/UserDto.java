package com.myproject.dto.dto;

import com.myproject.domain.entity.History;
import com.myproject.domain.entity.Scooter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.access.annotation.Secured;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int id;
    private String username;
    private String email;
    private String password;
    private String role;
    private Set<History> histories;
}

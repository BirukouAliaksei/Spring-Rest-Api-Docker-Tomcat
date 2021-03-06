package com.myproject.dto.mapper;

import com.myproject.domain.entity.User;
import com.myproject.dto.dto.UserDto;
import lombok.extern.log4j.Log4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j
@Component
public class UserMapper implements Mapper<User, UserDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User toEntity(UserDto dto) {
        return modelMapper.map(dto, User.class);
    }

    @Override
    public UserDto toDto(User entity) {
        return modelMapper.map(entity, UserDto.class);
    }
}

package com.myproject.dto.mapper;

import com.myproject.domain.entity.Scooter;
import com.myproject.dto.dto.ScooterDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ScooterMapper implements Mapper<Scooter, ScooterDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Scooter toEntity(ScooterDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, Scooter.class);
    }

    @Override
    public ScooterDto toDto(Scooter entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, ScooterDto.class);
    }
}

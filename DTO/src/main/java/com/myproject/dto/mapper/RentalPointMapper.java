package com.myproject.dto.mapper;

import com.myproject.domain.entity.RentalPoint;
import com.myproject.dto.dto.RentalPointDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RentalPointMapper implements Mapper<RentalPoint, RentalPointDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RentalPoint toEntity(RentalPointDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, RentalPoint.class);
    }

    @Override
    public RentalPointDto toDto(RentalPoint entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, RentalPointDto.class);
    }
}

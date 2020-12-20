package com.myproject.dto.mapper;

import com.myproject.domain.entity.RentalPoint;
import com.myproject.dto.dto.RentalPointDetailsDto;
import com.myproject.dto.dto.RentalPointDto;
import lombok.extern.log4j.Log4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Log4j
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

    public RentalPointDetailsDto toDtoWithDetails(RentalPoint entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, RentalPointDetailsDto.class);
    }
}

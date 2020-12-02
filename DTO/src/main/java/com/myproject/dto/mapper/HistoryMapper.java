package com.myproject.dto.mapper;

import com.myproject.domain.entity.History;
import com.myproject.dto.dto.HistoryDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class HistoryMapper implements Mapper<History, HistoryDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public History toEntity(HistoryDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, History.class);
    }

    @Override
    public HistoryDto toDto(History entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, HistoryDto.class);
    }
}

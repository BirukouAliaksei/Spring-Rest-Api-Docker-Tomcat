package com.myproject.dto.dto;

import com.myproject.domain.entity.User;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class HistoryDto {

    private int id;
    private int userId;
    private Double mileage;
    @DateTimeFormat
    private LocalDateTime startTime;
    @DateTimeFormat
    private LocalDateTime finishTime;
    private int startLocationId;
    private int finishLocationId;
    private String offerType;
    private int offerCost;
    private int scooterId;


}

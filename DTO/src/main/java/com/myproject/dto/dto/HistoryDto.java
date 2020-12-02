package com.myproject.dto.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class HistoryDto {

    private int id;
    //
    private int userId;
    //
    private Double mileade;
    @DateTimeFormat
    private LocalDateTime startTime;

    @DateTimeFormat
    private LocalDateTime finishTime;

    private int startLocationId;

    private int finishLocationId;
    //
    private String offerType;
    //
    private int offerCost;
    //
    private int scooterId;

//    private RentalPoint rentalPoint;

//    private Scooter scooter;

    public HistoryDto() {
    }

}

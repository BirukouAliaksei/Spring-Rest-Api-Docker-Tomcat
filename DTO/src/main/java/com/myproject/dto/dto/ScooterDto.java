package com.myproject.dto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScooterDto {

    private int id;
    private Double cost;
    private String model;
    private boolean availability;
    private int battery;
    private int rentalPointId;
}

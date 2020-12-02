package com.myproject.dto.dto;

import lombok.Data;

@Data
public class ScooterDto {

    private int id;

    private Double cost;

    private String model;

    private boolean availability;

    private int battery;

    private int rentalPointId;

//    private List<History> histories;

    public ScooterDto() {
    }

}

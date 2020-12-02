package com.myproject.dto.dto;

import com.myproject.domain.entity.History;
import lombok.Data;

import java.util.List;

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

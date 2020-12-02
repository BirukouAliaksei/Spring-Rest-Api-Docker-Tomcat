package com.myproject.dto.dto;

import com.myproject.domain.entity.History;
import com.myproject.domain.entity.Scooter;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class RentalPointDto {

    private int id;

    private String address;

    private int parentId;

    private int latitude;

    private int longitude;

    private List<History> histories;

//    private Scooter scooters;

    public RentalPointDto() {
    }
}

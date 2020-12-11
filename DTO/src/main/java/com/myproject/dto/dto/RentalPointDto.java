package com.myproject.dto.dto;

import com.myproject.domain.entity.Scooter;
import lombok.Data;

import java.util.Set;

@Data
public class RentalPointDto {

    private int id;
    private String address;
    private int parentId;
    private int latitude;
    private int longitude;

    private Set<Scooter> scooters;

}

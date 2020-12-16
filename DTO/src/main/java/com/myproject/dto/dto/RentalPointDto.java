package com.myproject.dto.dto;

import com.myproject.domain.entity.Scooter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalPointDto {

    private int id;
    private String address;
    private int parentId;
    private int latitude;
    private int longitude;

    private Set<Scooter> scooters;

}

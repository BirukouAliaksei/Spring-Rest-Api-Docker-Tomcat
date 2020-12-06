package com.myproject.dto.dto;

import lombok.Data;

@Data
public class RentalPointDto {

    private int id;
    private String address;
    private int parentId;
    private int latitude;
    private int longitude;

}

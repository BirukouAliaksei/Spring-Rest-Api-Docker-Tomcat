package com.myproject.serviceapi;

import com.myproject.dto.dto.RentalPointDto;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public interface RentalPointServiceApi {

    ArrayList<RentalPointDto> findAll();

    RentalPointDto save(RentalPointDto entity);

    void delete(int id);

    RentalPointDto update(RentalPointDto entity, int id);

    RentalPointDto rentalPointInfoById(int id);

}

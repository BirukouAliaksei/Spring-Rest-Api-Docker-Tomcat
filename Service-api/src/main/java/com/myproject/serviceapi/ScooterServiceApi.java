package com.myproject.serviceapi;

import com.myproject.dto.dto.ScooterDto;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public interface ScooterServiceApi {

    ArrayList<ScooterDto> findAll();

    ScooterDto save(ScooterDto entity);

    HttpStatus delete(int id);

    ScooterDto update(ScooterDto entity, int id);

    void ScooterTariffication (Double rate);

    ScooterDto findById(int id);

    ArrayList<ScooterDto> findRentalPointScootersById(int id);

    void setScooterPrice(Double price, int id);
}

package com.myproject.serviceapi;

import com.myproject.domain.entity.Scooter;
import com.myproject.dto.dto.ScooterDto;

import java.util.ArrayList;

public interface ScooterServiceApi {

    ArrayList<ScooterDto> findAll();

    void save(ScooterDto entity);

    void delete(int id);

    ScooterDto update(ScooterDto entity, int id);

    void ScooterTariffication (Double rate);

    ScooterDto findById(int id);

    ArrayList<ScooterDto> findRentalPointScootersById(int id);

    void setScooterPrice(Double price, int id);
}

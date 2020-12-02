package com.myproject.serviceapi;

import com.myproject.dto.dto.UserDto;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public interface UserServiceApi {

    ArrayList<UserDto> findAll();

    UserDto save(UserDto entity);

    HttpStatus delete(int id);

    UserDto update(UserDto entity, int id);

    UserDto findById(int id);

    void startTrip(int id, int scooterId, String offerType, String discount);

    void finishTrip(int id, int finishLocationId, Double mileage);
}

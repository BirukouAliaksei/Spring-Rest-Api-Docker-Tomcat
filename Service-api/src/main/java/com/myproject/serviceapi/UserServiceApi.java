package com.myproject.serviceapi;

import com.myproject.dto.dto.HistoryDto;
import com.myproject.dto.dto.UserDto;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public interface UserServiceApi {

    ArrayList<UserDto> findAll();

    UserDto save(UserDto entity);

    HttpStatus delete(int id);

    UserDto update(UserDto entity, int id);

    UserDto findById(int id);

    HistoryDto startTrip(int id, HistoryDto historyDto);

    HistoryDto finishTrip(int id, HistoryDto historyDto, int historyId);
}

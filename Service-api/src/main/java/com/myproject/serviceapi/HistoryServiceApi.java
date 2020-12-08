package com.myproject.serviceapi;

import com.myproject.dto.dto.HistoryDto;

import java.util.ArrayList;

public interface HistoryServiceApi {

    ArrayList<HistoryDto> findAll();

    HistoryDto findById(int id);

}

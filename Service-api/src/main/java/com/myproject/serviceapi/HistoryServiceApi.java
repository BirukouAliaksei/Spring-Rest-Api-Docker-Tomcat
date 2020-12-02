package com.myproject.serviceapi;

import com.myproject.dto.dto.HistoryDto;

import java.util.ArrayList;

public interface HistoryServiceApi {

    ArrayList<HistoryDto> findAll();

    void save(HistoryDto entity);

    void delete(int id);

    HistoryDto update(HistoryDto entity);

    HistoryDto findById(int id);

    ArrayList<HistoryDto> findAllHistoryById(int id);

    ArrayList<HistoryDto> findScooterHistoryById(int id);
}

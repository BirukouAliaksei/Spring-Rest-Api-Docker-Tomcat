package com.myproject.rest;

import com.myproject.dto.dto.HistoryDto;
import com.myproject.serviceapi.HistoryServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryServiceApi historyServiceApi;

    @GetMapping("/{id}")
    @Secured(value = "ROLE_ADMIN")
    public HistoryDto findById(@PathVariable("id") int id) {
        return historyServiceApi.findById(id);
    }


    @PutMapping(value = "/")
    @Secured(value = "ROLE_ADMIN")
    public void update(@RequestBody HistoryDto historyDto) {
        historyServiceApi.update(historyDto);
    }


    @GetMapping
    @Secured(value = "ROLE_ADMIN")
    public ArrayList<HistoryDto> findAll() {
        return historyServiceApi.findAll();
    }


}

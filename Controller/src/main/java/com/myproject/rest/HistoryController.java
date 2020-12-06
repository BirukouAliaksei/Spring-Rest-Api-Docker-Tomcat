package com.myproject.rest;

import com.myproject.dto.dto.HistoryDto;
import com.myproject.serviceapi.HistoryServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryServiceApi historyServiceApi;

    @GetMapping("/{id}")
//    @Secured(value = "ROLE_ADMIN")
    public HistoryDto findById(@PathVariable("id") int id) {
        return historyServiceApi.findById(id);
    }

    @PostMapping
//    @Secured(value = "ROLE_ADMIN")
    public void create(@RequestBody HistoryDto historyDto) {
        historyServiceApi.save(historyDto);
    }

    @PutMapping(value = "/")
//    @Secured(value = "ROLE_ADMIN")
    public void update(@RequestBody HistoryDto historyDto) {
        historyServiceApi.update(historyDto);
    }

    @DeleteMapping(value = "/{id}")
//    @Secured(value = "ROLE_ADMIN")
    public void delete(@PathVariable("id") int id) {
        historyServiceApi.delete(id);
    }

    @GetMapping
//    @Secured(value = "ROLE_ADMIN")
    public ArrayList<HistoryDto> findAll() {
        return historyServiceApi.findAll();
    }


}

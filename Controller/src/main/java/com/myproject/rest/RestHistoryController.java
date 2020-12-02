package com.myproject.rest;

import com.myproject.domain.entity.History;
import com.myproject.dto.dto.HistoryDto;
import com.myproject.dto.dto.ScooterDto;
import com.myproject.serviceapi.HistoryServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/history")
public class RestHistoryController {

    @Autowired
    private HistoryServiceApi historyServiceApi;

    @GetMapping("/{id}")
//    @Secured(value = "ROLE_ADMIN")
    public HistoryDto findById(@PathVariable("id") int id) {
        return historyServiceApi.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @Secured(value = "ROLE_ADMIN")
    public void create(@RequestBody HistoryDto historyDto) {
        historyServiceApi.save(historyDto);
    }

    @PutMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
//    @Secured(value = "ROLE_ADMIN")
    public void update(@RequestBody HistoryDto historyDto) {
        historyServiceApi.update(historyDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
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

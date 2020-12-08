package com.myproject.rest;

import com.myproject.dto.dto.HistoryDto;
import com.myproject.dto.dto.ScooterAdminDto;
import com.myproject.dto.dto.ScooterDto;
import com.myproject.serviceapi.HistoryServiceApi;
import com.myproject.serviceapi.ScooterServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/scooters")
public class ScooterController {

    @Autowired
    private ScooterServiceApi scooterServiceApi;

    @Autowired
    private HistoryServiceApi historyServiceApi;

    @PostMapping
    @Secured(value = "ROLE_ADMIN")
    public ScooterDto create(@RequestBody ScooterDto scooterDto) {
        return scooterServiceApi.save(scooterDto);
    }

    @PutMapping(value = "/{id}")
    @Secured(value = "ROLE_ADMIN")
    public ScooterDto update(@RequestBody ScooterDto scooterDto, @PathVariable("id") int id) {
        return scooterServiceApi.update(scooterDto, id);
    }

    @DeleteMapping(value = "/{id}")
    @Secured(value = "ROLE_ADMIN")
    public HttpStatus delete(@PathVariable("id") int id) {
        return scooterServiceApi.delete(id);
    }

    @GetMapping
    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    public ArrayList<ScooterDto> findAll() {
        return scooterServiceApi.findAll();
    }

    @GetMapping(value = "/{id}")
    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    public ScooterDto findById(@PathVariable("id") int id) {
        return scooterServiceApi.findById(id);
    }

    @GetMapping(value = "/{id}/history")
    @Secured(value = "ROLE_ADMIN")
    public ScooterAdminDto findScooterById(@PathVariable("id") int id) {
        return scooterServiceApi.findScooterById(id);
    }

//    @GetMapping(value = "/{id}/history")
//    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
//    public ArrayList<HistoryDto> findScooterHistoryById(@PathVariable("id") int id) {
//        return historyServiceApi.findScooterHistoryById(id);
//    }
}

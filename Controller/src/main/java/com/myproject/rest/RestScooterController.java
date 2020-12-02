package com.myproject.rest;

import com.myproject.dto.dto.HistoryDto;
import com.myproject.dto.dto.ScooterDto;
import com.myproject.serviceapi.HistoryServiceApi;
import com.myproject.serviceapi.ScooterServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/scooters")
public class RestScooterController {

    @Autowired
    private ScooterServiceApi scooterServiceApi;

    @Autowired
    private HistoryServiceApi historyServiceApi;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @Secured(value = "ROLE_ADMIN")
    public void create(@RequestBody ScooterDto scooterDto) {
        scooterServiceApi.save(scooterDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
//    @Secured(value = "ROLE_ADMIN")
    public void update(@RequestBody ScooterDto scooterDto, @PathVariable("id") int id) {
        scooterServiceApi.update(scooterDto, id);
    }

    @PutMapping(value = "/{id}/set-price")
    @ResponseStatus(HttpStatus.OK)
    public String setScooterPrice(@RequestParam("cost") Double cost,
                                  @PathVariable("id") int id) {
        scooterServiceApi.setScooterPrice(cost, id);
        return "scooter price is update";
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
//    @Secured(value = "ROLE_ADMIN")
    public void delete(@PathVariable("id") int id) {
        scooterServiceApi.delete(id);
    }

    @GetMapping
//    @Secured(value = "ROLE_ADMIN")
    public ArrayList<ScooterDto> findAll() {
        return scooterServiceApi.findAll();
    }

    @GetMapping(value = "/{id}")
//    @Secured(value = "ROLE_ADMIN")
    public ScooterDto findById(@PathVariable("id") int id) {
        return scooterServiceApi.findById(id);
    }

    @GetMapping(value = "/{id}/history")
//    @Secured(value = "ROLE_ADMIN")
    public ArrayList<HistoryDto> findScooterHistoryById(@PathVariable("id") int id) {
        return historyServiceApi.findScooterHistoryById(id);
    }
}

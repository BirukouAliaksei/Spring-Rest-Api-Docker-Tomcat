package com.myproject.rest;

import com.myproject.dto.dto.RentalPointDto;
import com.myproject.dto.dto.ScooterDto;
import com.myproject.serviceapi.RentalPointServiceApi;
import com.myproject.serviceapi.ScooterServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/rental_points")
public class RentalPointController {

    @Autowired
    private RentalPointServiceApi rentalPointServiceApi;

    @Autowired
    private ScooterServiceApi scooterServiceApi;

    @ResponseBody
    @GetMapping(value = "/{id}/scooters")
    public ArrayList<ScooterDto> findRentalPointScootersById(@PathVariable("id") int id) {
        return scooterServiceApi.findRentalPointScootersById(id);
    }

    @PostMapping
//    @Secured(value = "ROLE_ADMIN")
    public RentalPointDto create(@RequestBody RentalPointDto rentalPointDto) {
        return rentalPointServiceApi.save(rentalPointDto);
    }

    @PutMapping(value = "/{id}")
//    @Secured(value = "ROLE_ADMIN")
    public void update(@RequestBody RentalPointDto rentalPointDto, @PathVariable("id") int id) {
        rentalPointServiceApi.update(rentalPointDto, id);
    }

    @DeleteMapping(value = "/{id}")
//    @Secured(value = "ROLE_ADMIN")
    public HttpStatus delete(@PathVariable("id") int id) {
        return rentalPointServiceApi.delete(id);
    }

    @GetMapping
//    @Secured(value = "ROLE_ADMIN")
    public ArrayList<RentalPointDto> findAll() {
        return rentalPointServiceApi.findAll();
    }

    @GetMapping(value = "/{id}")
//    @Secured(value = "ROLE_ADMIN")
    public RentalPointDto findById(@PathVariable("id") int id) {
        return rentalPointServiceApi.rentalPointInfoById(id);
    }
}

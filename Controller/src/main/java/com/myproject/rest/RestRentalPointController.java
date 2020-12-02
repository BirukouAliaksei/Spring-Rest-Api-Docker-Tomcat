package com.myproject.rest;

import com.myproject.domain.entity.RentalPoint;
import com.myproject.dto.dto.RentalPointDto;
import com.myproject.dto.dto.ScooterDto;
import com.myproject.serviceapi.RentalPointServiceApi;
import com.myproject.serviceapi.ScooterServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/rental-points")
public class RestRentalPointController {

    @Autowired
    private RentalPointServiceApi rentalPointServiceApi;

    @Autowired
    private ScooterServiceApi scooterServiceApi;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/{id}/scooters")
    public ArrayList<ScooterDto> findRentalPointScootersById(@PathVariable("id") int id) {
        return scooterServiceApi.findRentalPointScootersById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @Secured(value = "ROLE_ADMIN")
    public void create(@RequestBody RentalPointDto rentalPointDto) {
        rentalPointServiceApi.save(rentalPointDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
//    @Secured(value = "ROLE_ADMIN")
    public void update(@RequestBody RentalPointDto rentalPointDto, @PathVariable("id") int id) {
        rentalPointServiceApi.update(rentalPointDto, id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
//    @Secured(value = "ROLE_ADMIN")
    public void delete(@PathVariable("id") int id) {
        rentalPointServiceApi.delete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
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

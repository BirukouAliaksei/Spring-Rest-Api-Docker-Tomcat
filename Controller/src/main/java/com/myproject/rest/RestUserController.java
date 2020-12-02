package com.myproject.rest;

import com.myproject.dto.dto.HistoryDto;
import com.myproject.dto.dto.UserDto;
import com.myproject.serviceapi.HistoryServiceApi;
import com.myproject.serviceapi.UserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class RestUserController {

    @Autowired
    private UserServiceApi userServiceApi;

    @Autowired
    private HistoryServiceApi historyServiceApi;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @Secured(value = "ROLE_ADMIN")
    public UserDto create(@RequestBody UserDto userDto) {
        return userServiceApi.save(userDto);
    }

    @PostMapping(value = "/{id}/history")
    @ResponseStatus(HttpStatus.CREATED)
//    @Secured(value = "ROLE_ADMIN")
    public void startTrip(@PathVariable("id") int id,
                          @RequestParam("scooter_id") int scooterId,
                          @RequestParam("offer_type") String offerType,
                          @RequestParam("discount") String discount) {
        userServiceApi.startTrip(id, scooterId, offerType, discount);
    }

    @PutMapping(value = "/{id}/history")
    @ResponseStatus(HttpStatus.CREATED)
//    @Secured(value = "ROLE_ADMIN")
    public void finishTrip(@PathVariable("id") int id,
                           @RequestParam("finish_location_id") int finishLocationId,
                           @RequestParam("mileage") Double mileage) {
        userServiceApi.finishTrip(id, finishLocationId, mileage);
    }


    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
//    @Secured(value = "ROLE_ADMIN")
    public UserDto update(@RequestBody UserDto userDto, @PathVariable("id") int id) {
        return userServiceApi.update(userDto, id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
//    @Secured(value = "ROLE_ADMIN")
    public @ResponseBody void deleteById(@PathVariable("id") int id) {
        userServiceApi.delete(id);
    }

    @GetMapping
//    @Secured(value = "ROLE_ADMIN")
    public ArrayList<UserDto> findAll() {
        return userServiceApi.findAll();
    }


    @GetMapping(value = "/{id}")
    public @ResponseBody UserDto findById(@PathVariable("id") int id) {
        return userServiceApi.findById(id);
    }

    @GetMapping(value = "/{id}/history")
    public ArrayList<HistoryDto> findHistoryById(@PathVariable("id") int id) {
        return historyServiceApi.findAllHistoryById(id);
    }
}


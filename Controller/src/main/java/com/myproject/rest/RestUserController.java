package com.myproject.rest;

import com.myproject.dto.dto.HistoryDto;
import com.myproject.dto.dto.UserDto;
import com.myproject.serviceapi.HistoryServiceApi;
import com.myproject.serviceapi.UserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
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
    public HistoryDto startTrip(@PathVariable("id") int id, @RequestBody HistoryDto historyDto) {
        return userServiceApi.startTrip(id, historyDto);
    }

    @PutMapping(value = "/{id}/history/{historyId}")
    @ResponseStatus(HttpStatus.CREATED)
//    @Secured(value = "ROLE_ADMIN")
    public HistoryDto finishTrip(@PathVariable("id") int id, @RequestBody HistoryDto historyDto, @PathVariable("historyId") int historyId) {
        return userServiceApi.finishTrip(id, historyDto, historyId);
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
    public HttpStatus deleteById(@PathVariable("id") int id) {
        return userServiceApi.delete(id);
    }

    @GetMapping
//    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    public ArrayList<UserDto> findAll() {
        return userServiceApi.findAll();
    }


    @GetMapping(value = "/{id}")
//    @PreAuthorize("hasRole('USER')")
    public @ResponseBody
    UserDto findById(@PathVariable("id") int id) {
        return userServiceApi.findById(id);
    }

    @GetMapping(value = "/{id}/history")
    public ArrayList<HistoryDto> findHistoryById(@PathVariable("id") int id) {
        return historyServiceApi.findHistoryByUserId(id);
    }
}


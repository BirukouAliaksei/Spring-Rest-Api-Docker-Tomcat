package com.myproject.rest;

import com.myproject.dto.dto.HistoryDto;
import com.myproject.serviceapi.UserServiceApi;
import com.myproject.serviceimpl.HistoryService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
@RequestMapping("/trip")
public class TripController {

    @Autowired
    private UserServiceApi userServiceApi;

    @PostMapping(value = "/{id}/start_trip")
    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    public HistoryDto startTrip(@PathVariable("id") int id, @RequestBody HistoryDto historyDto) {
        return userServiceApi.startTrip(id, historyDto);
    }

    @PutMapping(value = "/{id}/finish_trip/{historyId}")
    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    public HistoryDto finishTrip(@PathVariable("id") int id, @RequestBody HistoryDto historyDto, @PathVariable("historyId") int historyId) {
        return userServiceApi.finishTrip(id, historyDto, historyId);
    }
}

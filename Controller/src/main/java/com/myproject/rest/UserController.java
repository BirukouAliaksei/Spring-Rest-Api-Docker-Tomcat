package com.myproject.rest;

import com.myproject.dto.dto.UserDto;
import com.myproject.serviceapi.HistoryServiceApi;
import com.myproject.serviceapi.UserServiceApi;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Log4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceApi userServiceApi;

    @Autowired
    private HistoryServiceApi historyServiceApi;


    @PostMapping
    @Secured(value = "ROLE_ADMIN")
    public UserDto create(@RequestBody UserDto userDto) {
        return userServiceApi.save(userDto);
    }


    @PutMapping(value = "/{id}")
    @Secured(value = "ROLE_ADMIN")
    public UserDto update(@RequestBody UserDto userDto, @PathVariable("id") int id) {
        return userServiceApi.update(userDto, id);
    }


    @DeleteMapping(value = "/{id}")
    @Secured(value = "ROLE_ADMIN")
    public String deleteById(@PathVariable("id") int id) {
        return userServiceApi.delete(id);
    }

    @GetMapping
    @Secured(value = "ROLE_ADMIN")
    public ArrayList<UserDto> findAll() {
        return userServiceApi.findAll();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "My App Service get test1 API")
    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    public @ResponseBody
    ArrayList<UserDto> findById(@PathVariable("id") int id) {
        return userServiceApi.findByIdWithHistory(id);
    }
}


//    @GetMapping(value = "/{id}/history")
//    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
//    public ArrayList<HistoryDto> findHistoryById(@PathVariable("id") int id) {
//        return historyServiceApi.findHistoryByUserId(id);
//    }
//}


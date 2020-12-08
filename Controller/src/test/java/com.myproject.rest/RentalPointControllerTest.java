package com.myproject.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.myproject.dto.dto.RentalPointDto;
import com.myproject.dto.dto.UserDto;
import com.myproject.serviceapi.RentalPointServiceApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class RentalPointControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private RentalPointController rentalPointController;

    @Mock
    private RentalPointServiceApi rentalPointServiceApi;

    String createJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(new UserDto());
        return requestJson;
    }

    @BeforeEach
    void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(rentalPointController).build();
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/rental_points")
                .content(createJson())
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(put("/rental_points/{id}", 1)
                .content(createJson())
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/rental_points/{id}", "1")
                .content(createJson())
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/rental_points")).andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        ArrayList<RentalPointDto> rentalPointDtos = new ArrayList<>();
        rentalPointDtos.add(new RentalPointDto());

        given(rentalPointServiceApi.findAll()).
                willReturn(rentalPointDtos);

        mockMvc.perform(get("/rental_points/{id}", 0)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
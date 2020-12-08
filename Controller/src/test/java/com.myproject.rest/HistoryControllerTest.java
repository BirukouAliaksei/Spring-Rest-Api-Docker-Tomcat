package com.myproject.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myproject.dto.dto.HistoryDto;
import com.myproject.serviceapi.HistoryServiceApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class HistoryControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private HistoryController historyController;

    @Mock
    private HistoryServiceApi historyServiceApi;

    @BeforeEach
    void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(historyController).build();
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/history")).andExpect(status().isOk());
    }

    @Test
    void findAll() throws Exception {
        ArrayList<HistoryDto> historyDtos = new ArrayList<>();
        historyDtos.add(new HistoryDto());

        given(historyServiceApi.findAll()).
                willReturn(historyDtos);

        mockMvc.perform(get("/history/{id}", 0)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}